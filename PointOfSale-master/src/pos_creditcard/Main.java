package pos_creditcard;

import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    PointOfSale pointOfSale = new PointOfSale();
    // Some customers arrive, sit at a table, the waiter comes and makes a new sale.
    // Then takes the order : 4 Moritz and 1 Coca-cola
    //SALE 0
    int idSale = pointOfSale.makeNewSale("optimal");
    pointOfSale.addLineItemToSale(idSale, "Moritz", 4);
    System.out.println("ordered 4 Moritz");
    pointOfSale.addLineItemToSale(idSale, "Coca-cola", 1);
    System.out.println("ordered 1 Coca-cola");
    pointOfSale.addLineItemToSale(idSale, "Coca-cola", 2);
    System.out.println("ordered 2 Coca-cola");
    // ask for the bill and the waiter prints the receipt:
    pointOfSale.printReceiptOfSale(idSale);
    //Money Handed
    HashMap<Double, Integer> moneyHanded = new HashMap();
    moneyHanded.put(10.0, 2);
    pointOfSale.payOneSaleCash(idSale, moneyHanded);
    //print change
    pointOfSale.printPayment(idSale);

    //SALE 1
    int idSale1 = pointOfSale.makeNewSale("not");
    System.out.println("Sale 2");
    pointOfSale.addLineItemToSale(idSale1, "Moritz", 4);
    System.out.println("ordered 4 Moritz");
    pointOfSale.addLineItemToSale(idSale1, "Coca-cola", 1);
    System.out.println("ordered 1 Coca-cola");
    pointOfSale.addLineItemToSale(idSale1, "Coca-cola", 2);
    System.out.println("ordered 2 Coca-cola");
    // ask for the bill and the waiter prints the receipt:
    pointOfSale.printReceiptOfSale(idSale1);
    //Money Handed
    HashMap<Double, Integer> moneyHanded1 = new HashMap();
    moneyHanded1.put(10.0, 2);
    pointOfSale.payOneSaleCash(idSale1, moneyHanded1);
    //print change
    pointOfSale.printPayment(idSale1);

    //SALE 2
    int idSale2 = pointOfSale.makeNewSale("");
    pointOfSale.addLineItemToSale(idSale2, "Nestea", 1);
    pointOfSale.printReceiptOfSale(idSale2);
    pointOfSale.payOneSaleCreditCard(idSale2, "4502360043567891");
    pointOfSale.printPayment(idSale2);

    pointOfSale.printCashAmountState();
  }
}