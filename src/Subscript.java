public class Subscript implements Function {
  private int index;
  public Subscript(int index) {
    this.index = index-1;
  }

  @Override
  public DualNumber evaluate(double[] x) { // not a dual number anymore
    int num_dimensions = x.length;
    double[] uprime = new double[num_dimensions];
    for (int i=0; i<num_dimensions; i++) {
      uprime[i] = (i==index) ? 1.0 : 0.0;
    }
    return new DualNumber(x[index], uprime);
  }