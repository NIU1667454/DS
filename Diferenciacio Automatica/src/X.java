public class X implements Function {
  public X() {}

  @Override
  public DualNumber evaluate(DualNumber dn) {
    return new DualNumber(dn.u, 1.0);  // La derivada de x respecto a x es 1
  }
}
