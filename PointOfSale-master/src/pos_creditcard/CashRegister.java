package pos_creditcard;

import java.util.HashMap;

public class CashRegister {
  private int id;
  private HashMap<Double, Integer> cashAmounts;

  public CashRegister(HashMap<Double, Integer> cashAmounts) {
    this.cashAmounts = cashAmounts;

    for (HashMap.Entry<Double, Integer> entry : cashAmounts.entrySet()) {
      this.cashAmounts.put(entry.getKey(), entry.getValue());
    }
    printCashAmountIni();
  }

  public void printCashAmountIni() {
    System.out.println("cash box initially loaded with");
    for (HashMap.Entry<Double, Integer> entry : cashAmounts.entrySet()) {
      System.out.println(entry.getValue() + " of " + entry.getKey());
    }
  }

  public void printCashAmountState() {
    System.out.println("after payment and giving change the cash box has");
    for (HashMap.Entry<Double, Integer> entry : cashAmounts.entrySet()) {
      if (entry.getValue() != 0)
        System.out.println(entry.getValue() + " of " + entry.getKey());
    }
  }

  public HashMap<Double, Integer> getCashAmountState() { return cashAmounts; }
  public int getAvailable(double coin) { return cashAmounts.get(coin);}
  public void update(double coin, int num) {
    int available = cashAmounts.get(coin);
    cashAmounts.put(coin, available + num); }
}