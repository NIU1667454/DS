package pos_creditcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PaymentInCashRandom extends PaymentInCash{
  public PaymentInCashRandom(HashMap<Double, Integer> amountHanded, double amountToPay, HashMap<Double, Integer> cashAmounts)
  {
    super(amountHanded, amountToPay, cashAmounts);
  }


  public double change()
  {
    double change = amountHanded - amountToPay;
    assert change >= 0;
    int attempts = 0;
    Random random = new Random();
    ArrayList<Double> denominations = new ArrayList<>(cashAmounts.keySet()); // Lista de denominaciones disponibles

    while (change > 0 && attempts < 100)
    {
      // Seleccionamos una denominación aleatoria
      double selectedCash = denominations.get(random.nextInt(denominations.size()));
      attempts++;
      // Verificamos si hay suficientes monedas/billetes de esta denominación
      int available = cashAmounts.get(selectedCash);

      if (change >= selectedCash && available > 0) {

        change = change -= selectedCash;
        change = Math.round(change*1000);
        change = change / 1000;

        // Reducimos la cantidad de esta denominación disponible
        cashAmounts.put(selectedCash, available - 1);

        // Registramos el cambio dado
        changeGiven.put(selectedCash, changeGiven.getOrDefault(selectedCash, 0) + 1);
      }
    }
    if(attempts == 100)
      System.out.println("not sufficient notes");
    assert change == 0;
    return amountHanded - amountToPay; // Retorna el cambio dado
  }

  @Override
  public void print()
  {
    System.out.println("total to pay " + amountToPay + ", change to give " + change());
    System.out.println("the change is ");
    for (HashMap.Entry<Double, Integer> entry : changeGiven.entrySet()) {
      if(entry.getValue() > 0)
        System.out.println(entry.getValue() + " of " + entry.getKey() );
    }
  }
}