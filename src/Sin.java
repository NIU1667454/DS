public class Sin implements Function {
  private Function func;

  public Sin(Function func) { this.func = func; }

  @Override
  public DualNumber evaluate(double[] x)
  {
    DualNumber dn = func.evaluate(x);
    int num_dimensions = dn.uprime.length;
    double[] uprime = new double[num_dimensions];

    for (int i=0; i<num_dimensions ; i++)
      uprime[i] = dn.uprime[i]*Math.cos(dn.u);

    return new DualNumber(Math.sin(dn.u), uprime);
  }
}