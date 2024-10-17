public class Add extends BinaryOperator{

  public Add(Function f1, Function f2) { super(f1, f2);}

  @Override
  public DualNumber evaluate(DualNumber dn) {
    DualNumber dn1 = func1.evaluate(dn);
    DualNumber dn2 = func2.evaluate(dn);
    return new DualNumber(dn1.u + dn2.u, dn1.uprime + dn2.uprime);
  }
}
