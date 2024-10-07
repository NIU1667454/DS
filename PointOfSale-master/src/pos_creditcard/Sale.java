package pos_creditcard;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Sale {
  boolean isPaid = false;
  private int id;
  private ArrayList<SaleLineItem> saleLineItems = new ArrayList<>();
  private LocalDateTime dateTime = LocalDateTime.now();
  private Payment payment;  // note : supertype
  private String changeMaking;
  private CashRegister cashRegister;

  public Sale(int id, String changeMaking, CashRegister cashRegister) {
    this.id = id;
    this.changeMaking = changeMaking;
    this.cashRegister = cashRegister;
  }

  public int getId() {
    return id;
  }

  public void addLineItem(ProductSpecification productSpecification, int quantity) {
    for (SaleLineItem item : saleLineItems) {
      if (item.productSpecification == productSpecification) { // same object
        item.incrementQuantity(quantity);
        return;
      }
    }
    saleLineItems.add(new SaleLineItem(productSpecification, quantity));
  }

  private double total() {
    double total = 0.;
    for (SaleLineItem saleLineItem : saleLineItems) {
      total += saleLineItem.subtotal();
    }
    return total;
  }

  public void printReceipt() {
    System.out.println("Sale " + id);
    System.out.println(DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm").format(dateTime));
    for (SaleLineItem saleLineItem : saleLineItems) {
      saleLineItem.print();
    }
    System.out.printf("Total %.2f\n", total());
  }

  public void badPrintReceipt() {
    System.out.println("Sale " + id);
    System.out.println(DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm").format(dateTime));
    double total = 0.;
    for (SaleLineItem saleLineItem : saleLineItems) {
      String prodName = saleLineItem.productSpecification.getName();
      int quantity = saleLineItem.quantity; //getQuantity();
      double price = saleLineItem.productSpecification.getPrice();
      double subtotal = quantity * price;
      System.out.printf("%s %d x %.2f = %.2f\n", prodName, quantity, price, subtotal);
      total += subtotal;
    }
    System.out.printf("Total %.2f\n", total);
  }

  public void payCash(HashMap<Double, Integer> amountHanded) {
    assert !isPaid : "sale " + id + " has already been paid";
    if(changeMaking.equals("optimal"))
    {
      System.out.println("Make change with random change maker");
      payment = new PaymentInCashRandom(amountHanded, total(), cashRegister);
    }
    else
    {
      System.out.println("Make change with greedy change maker");
      payment = new PaymentInCashGreedy(amountHanded, total(), cashRegister);
    }
    System.out.println("money handed ");
    for (HashMap.Entry<Double, Integer> entry : amountHanded.entrySet()) {
      System.out.println(entry.getValue() + " of " + entry.getKey() );
      cashRegister.getCashAmountState().put(entry.getKey(), entry.getValue() + cashRegister.getCashAmountState().get(entry.getKey()));
    }
    System.out.println("added payment to cash box");

    isPaid = true;
  }

  public void payCreditCard(String ccnumber) {
    assert !isPaid : "sale " + id + " has already been paid";
    payment = new PaymentCreditCard(ccnumber, total());
    if ( ((PaymentCreditCard) payment).isAuthorized() ) {
      // note cast, necessary to call isAuthorized()
      isPaid = true;
    }
  }

  public void printPayment() {
    assert payment != null : "No payment for sale " + id;
    payment.print();
  }

  public boolean isPaid() {
    return isPaid;
  }

}
