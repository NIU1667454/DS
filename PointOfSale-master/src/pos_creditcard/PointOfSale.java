package pos_creditcard;

import java.util.ArrayList;
import java.util.HashMap;

public class PointOfSale {
  private ProductCatalog productCatalog;
  private ArrayList<Sale> sales;
  private int idLastSale = 0;
  private final String FILE_NAME = "src/pos/catalog.txt";
  HashMap<Double, Integer> cashAmounts;

  public PointOfSale() {
    productCatalog = new ProductCatalog(FILE_NAME);
    sales = new ArrayList<>();
    cashAmounts = new HashMap();
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

    System.out.println("cash box initially loaded with");
    for (HashMap.Entry<Double, Integer> entry : cashAmounts.entrySet()) {
      System.out.println(entry.getValue() + " of " + entry.getKey() );
    }
  }

  public int makeNewSale(String changeMaking) {
    idLastSale++;
    Sale newSale = new Sale(idLastSale, changeMaking);
    sales.add(newSale);
    return idLastSale;
  }

  public void addLineItemToSale(int idSale, String productName, int quantity) {
    ProductSpecification productSpecification = productCatalog.searchByName(productName);
    Sale sale = searchSaleById(idSale);
    sale.addLineItem(productSpecification, quantity);
  }

  private Sale searchSaleById(int id) {
    for (Sale s : sales) {
      if (s.getId() == id) {
        return s;
      }
    }
    return null;
  }

  public void printReceiptOfSale(int saleId) {
    Sale sale = searchSaleById(saleId);
    sale.printReceipt();
  }

  public void payOneSaleCash(int saleId, HashMap<Double, Integer> amountHanded) {
    Sale sale = searchSaleById(saleId);
    sale.payCash(amountHanded, cashAmounts);
  }

  public void payOneSaleCreditCard(int saleId, String ccnumber) {
    Sale sale = searchSaleById(saleId);
    sale.payCreditCard(ccnumber);
  }

  public void printPayment(int saleId) {
    Sale sale = searchSaleById(saleId);
    sale.printPayment();
  }

  public boolean isSalePaid(int id) {
    return searchSaleById(id).isPaid();
  }

  public void printCashAmountState() {
    System.out.println("after payment and giving change the cash box has");
    for (HashMap.Entry<Double, Integer> entry : cashAmounts.entrySet()) {
      if(entry.getValue() != 0)
        System.out.println(entry.getValue() + " of " + entry.getKey() );
    }
  }
  // this is for the user interface
  public ArrayList<String> getProductNames() {
    return productCatalog.getProductNames();
  }


}

