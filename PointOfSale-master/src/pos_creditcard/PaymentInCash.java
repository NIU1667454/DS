package pos_creditcard;
import java.util.HashMap;

public abstract class PaymentInCash extends Payment {
  double amountHanded;
  HashMap<Double, Integer> changeGiven;
  HashMap<Double, Integer> cashAmounts;
  public PaymentInCash(HashMap<Double, Integer> amountHanded, double amountToPay, HashMap<Double, Integer> cashAmounts) {
    super(amountToPay);
    double handed = 0;
    for (HashMap.Entry<Double, Integer> entry : amountHanded.entrySet()) {
      handed = handed + (entry.getValue()*entry.getKey());
    }

    assert handed >= amountToPay;
    this.amountHanded = handed;
    changeGiven = new HashMap<>();
    this.cashAmounts = cashAmounts;
    /*
    System.out.println("cash box initially loaded with");
    for (HashMap.Entry<Double, Integer> entry : cashAmounts.entrySet()) {
      System.out.println(entry.getValue() + " of " + entry.getKey() );
    }
     */
  }

  public abstract double change();

  @Override
  public void print() {
    change();
    System.out.println("total to pay " + amountToPay + ", change to give " + (amountToPay-amountHanded));
  }
}
