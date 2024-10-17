public class Sin implements Function {
  private final Function func;

  public Sin(Function f) { this.func = f; }

  @Override
  public DualNumber evaluate(DualNumber dn) {
    DualNumber dnEval = func.evaluate(dn);
    return new DualNumber(Math.sin(dnEval.u), dnEval.uprime * Math.cos(dnEval.u));
  }
}
