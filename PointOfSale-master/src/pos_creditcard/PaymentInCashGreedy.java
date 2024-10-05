package pos_creditcard;
import java.util.HashMap;


public class PaymentInCashGreedy extends PaymentInCash
{

  public PaymentInCashGreedy(double amountHanded, double amountToPay)
  {
    super(amountHanded, amountToPay);
  }

  public double change()
  {
    HashMap<Double, Integer> changeGiven = new HashMap<>();

    double change = amountHanded - amountToPay;
    assert change >= 0;

    for(double coinOrNote : cashAmounts.keySet())
    {
      changeGiven.put(coinOrNote, 0);
      int available = cashAmounts.get(coinOrNote);

      while(change >= coinOrNote && available > 0)
      {
        change -= coinOrNote;
        changeGiven.put(coinOrNote, changeGiven.get(coinOrNote) + 1);
        available--;
      }
    }

    assert change == 0;

    return amountHanded - amountToPay;
  }
}
