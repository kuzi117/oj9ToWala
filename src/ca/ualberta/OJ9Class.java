package ca.ualberta;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.classLoader.SyntheticClass;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.collections.HashSetFactory;
import com.ibm.wala.util.debug.Assertions;
import com.ibm.wala.util.strings.Atom;

import java.util.Collection;
import java.util.Set;

public class OJ9Class extends SyntheticClass {
  // The added methods.
  private Set<IMethod> methods;

  /**
   * @param T   type reference describing this class
   * @param cha
   */
  public OJ9Class(TypeReference T, IClassHierarchy cha) {
    super(T, cha);
    methods = HashSetFactory.make();
  }

  public void addMethod(IMethod m) {
    methods.add(m);
  }

  @Override
  public boolean isPublic() {
    return true;
  }

  @Override
  public boolean isPrivate() {
    return false;
  }

  @Override
  public int getModifiers() throws UnsupportedOperationException {
    return 0;
  }

  @Override
  public IClass getSuperclass() {
    return null;
  }

  @Override
  public Collection<? extends IClass> getDirectInterfaces() {
    return null;
  }

  @Override
  public Collection<IClass> getAllImplementedInterfaces() {
    throw new UnsupportedOperationException();
  }

  @Override
  public IMethod getMethod(Selector selector) {
    for (IMethod method : methods) {
      if (method.getSelector().equals(selector)) {
        return method;
      }
    }

    return null;
  }

  @Override
  public IField getField(Atom name) {
    return null;
  }

  @Override
  public IMethod getClassInitializer() {
    Assertions.UNREACHABLE();
    return null;
  }

  @Override
  public Collection<? extends IMethod> getDeclaredMethods() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<IField> getAllInstanceFields() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<IField> getAllStaticFields() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<IField> getAllFields() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<? extends IMethod> getAllMethods() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<IField> getDeclaredInstanceFields() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<IField> getDeclaredStaticFields() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isReferenceType() {
    return getReference().isReferenceType();
  }
}
