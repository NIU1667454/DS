public class Main {
  public static void main(String[] args) {
    //Evaluar exemple de les transparències
    Function x = new X();  // La variable x
    Function halfPi = new Constant(3.141592/2.0);
    Function x2 = new X(); // f(x) = x

    Function func = new Sin(new Add(halfPi, new Multiply(x, x))); // sin(0.5 \pi + x^2)
    DualNumber result2 = func.evaluate(new DualNumber(4.0, 1));

    System.out.println("Exemple de les transparències");
    System.out.println("Expected:\n f(4.0) = -0.9576594803233841\n f'(4.0) = 2.3032265333205353\n");
    System.out.println("Computed:");
    System.out.println("f(4.0) = " + result2.u);
    System.out.println("f'(4.0) = " + result2.uprime);
  }
}
