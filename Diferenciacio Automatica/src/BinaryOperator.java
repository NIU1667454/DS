public abstract class BinaryOperator implements Function {
  protected Function func1;
  protected Function func2;

  public BinaryOperator(Function f1, Function f2) {
    this.func1 = f1;
    this.func2 = f2;
  }
}