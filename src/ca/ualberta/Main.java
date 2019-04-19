package ca.ualberta;

import com.ibm.wala.cast.loader.SingleClassLoaderFactory;
import com.ibm.wala.classLoader.ClassLoaderFactory;
import com.ibm.wala.classLoader.ClassLoaderFactoryImpl;
import com.ibm.wala.classLoader.Language;
import com.ibm.wala.classLoader.SyntheticClass;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.summaries.BypassSyntheticClass;
import com.ibm.wala.model.SyntheticFactory;
import com.ibm.wala.types.*;
import com.ibm.wala.util.NullProgressMonitor;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.config.SetOfClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException, ClassHierarchyException {
    assert(args.length == 1);

    // Set up java base classes.
    AnalysisScope as = AnalysisScopeReader.readJavaScope("./classLoader.txt", null, Main.class.getClassLoader());

    // Make a class hierarchy, faking the root class.
    ClassHierarchy ch = ClassHierarchyFactory.makeWithRoot(as);

    // Read the input file.
    BridgeReader reader = new BridgeReader(args[0]);

    // Set up for MethodReference.
    TypeReference declaringClassType = TypeReference.findOrCreate(ClassLoaderReference.Application, "L" + reader.className + ";");

    // Make MethodReference.
    MethodReference methRef = MethodReference.findOrCreate(declaringClassType, reader.methodName, reader.signature);

    // Make our declaring class.
    OJ9Class declClass = new OJ9Class(declaringClassType, ch);
    OJ9Method methToAnalyse = new OJ9Method(methRef, declClass, reader.methodName, reader.signature, reader.lines);
    declClass.addMethod(methToAnalyse);

    for (Line line : reader.lines)
      line.print();
  }

  private static class BridgeReader {
    public String className;
    public String methodName;
    public String signature;
    public ArrayList<Line> lines;
    public BridgeReader(String filename) throws IOException {
      // Field init
      lines = new ArrayList<Line>();

      // Get lines.
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String tLine = reader.readLine();
      assert(tLine != null);

      String[] sigLine = tLine.split(":");
      assert(sigLine.length == 3);
      className = sigLine[0];
      methodName = sigLine[1];
      signature = sigLine[2];

      while ((tLine = reader.readLine()) != null) {
        lines.add(new Line(tLine.split(":")));
      }
    }
  }
}
