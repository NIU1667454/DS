public class Constant implements Function {
  private final double value;

  public Constant(double v) { this.value = v; }

  @Override
  public DualNumber evaluate(DualNumber dn) {
    return new DualNumber(value, 0.0);  // Derivada constant = 0
  }
}
