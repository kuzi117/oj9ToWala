package ca.ualberta;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.SyntheticMethod;
import com.ibm.wala.ipa.callgraph.Context;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAOptions;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.debug.UnimplementedError;

import java.util.ArrayList;

public class OJ9Method extends SyntheticMethod {
  ArrayList<Line> lines;

  public OJ9Method(MethodReference ref, IClass declaringClass, String name, String signature, ArrayList<Line> lines)
      throws NullPointerException {
    super(ref, declaringClass, false, false);
    System.out.println(ref.getNumberOfParameters());

    this.lines = lines;
  }

  @Override
  public IR makeIR(Context context, SSAOptions options) throws UnimplementedError {
    
    return super.makeIR(context, options);
  }
}

