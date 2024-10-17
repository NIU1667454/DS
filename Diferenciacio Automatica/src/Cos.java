public class Cos implements Function {
  private final Function func;

  public Cos(Function f) { this.func = f; }

  @Override
  public DualNumber evaluate(DualNumber dn) {
    DualNumber dnEval = func.evaluate(dn);
    return new DualNumber(Math.cos(dnEval.u), -dnEval.uprime * Math.sin(dnEval.u));
  }
}