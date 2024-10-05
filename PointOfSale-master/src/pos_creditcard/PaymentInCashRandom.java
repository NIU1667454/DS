package pos_creditcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PaymentInCashRandom extends PaymentInCash{
  public PaymentInCashRandom(double amountHanded, double amountToPay)
  {
    super(amountHanded, amountToPay);
  }


  public double change()
  {
    HashMap<Double, Integer> changeGiven = new HashMap<>();
    double change = amountHanded - amountToPay;
    assert change >= 0;

    Random random = new Random();
    ArrayList<Double> denominations = new ArrayList<>(cashAmounts.keySet()); // Lista de denominaciones disponibles

    while (change > 0)
    {
      // Seleccionamos una denominación aleatoria
      double selectedCash = denominations.get(random.nextInt(denominations.size()));

      // Verificamos si hay suficientes monedas/billetes de esta denominación
      int available = cashAmounts.get(selectedCash);

      if (change >= selectedCash && available > 0) {

        change -= selectedCash;

        // Reducimos la cantidad de esta denominación disponible
        cashAmounts.put(selectedCash, available - 1);

        // Registramos el cambio dado
        changeGiven.put(selectedCash, changeGiven.getOrDefault(selectedCash, 0) + 1);
      }
    }

    assert change == 0;
    return amountHanded - amountToPay; // Retorna el cambio dado
  }
}