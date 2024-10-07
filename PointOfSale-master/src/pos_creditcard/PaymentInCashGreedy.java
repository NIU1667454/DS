package pos_creditcard;
import java.util.HashMap;


public class PaymentInCashGreedy extends PaymentInCash
{
  //CONSTRUCTOR
  public PaymentInCashGreedy(HashMap<Double, Integer> amountHanded, double amountToPay, CashRegister cashRegister)
  {
    super(amountHanded, amountToPay, cashRegister);
  }

  //Calcula el canvi
  public double change()
  {

    double change = amountHanded - amountToPay;
    assert change >= 0;

    for(double coinOrNote : cashRegister.getCashAmountState().keySet())
    {
      if(change >= coinOrNote)
      {
        changeGiven.put(coinOrNote, 0);
        int available = cashRegister.getAvailable(coinOrNote);
        while(change >= coinOrNote && available > 0)
        {
          change -= coinOrNote;
          change = Math.round(change*1000);
          change = change / 1000;
          changeGiven.put(coinOrNote, changeGiven.get(coinOrNote) + 1);
          //cashAmounts.put(coinOrNote, available - 1);
          cashRegister.update(coinOrNote, -1);
          //available = cashAmounts.get(coinOrNote);
          available = cashRegister.getAvailable(coinOrNote);
        }
      }
    }
    if(change > 0)
      System.out.println("not sufficient notes");
    assert change == 0;

    return amountHanded - amountToPay;
  }

  @Override
  public void print()
  {
    System.out.println("total to pay " + amountToPay + ", change to give " + change());
    System.out.println("the change is ");
    for (HashMap.Entry<Double, Integer> entry : changeGiven.entrySet()) {
      if(entry.getValue() != 0)
        System.out.println(entry.getValue() + " of " + entry.getKey() );
    }
  }
}
