package pos_creditcard;
import java.util.HashMap;

public abstract class PaymentInCash extends Payment {
  double amountHanded;
  HashMap<Double, Integer> cashAmounts;

  public PaymentInCash(double amountHanded, double amountToPay) {
    super(amountToPay);
    assert amountHanded >= amountToPay;
    this.amountHanded = amountHanded;

    cashAmounts = new HashMap<>();

    cashAmounts.put(20.0, 5);
    cashAmounts.put(10.0, 5);
    cashAmounts.put(5.0, 5);
    cashAmounts.put(2.0, 5);
    cashAmounts.put(1.0, 5);
    cashAmounts.put(0.5, 5);
    cashAmounts.put(0.2, 5);
    cashAmounts.put(0.1, 5);
    cashAmounts.put(0.05, 5);
    cashAmounts.put(0.02, 5);
    cashAmounts.put(0.01, 5);
  }

  public abstract double change();

  @Override
  public void print() {
    System.out.printf("\nAmount handed : %.2f\nChange : %.2f\n", amountHanded, change());
  }
}
