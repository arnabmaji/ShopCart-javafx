package io.github.arnabmaji19.model;

public class TransactionDetails{
    private String productName;
    private String buyerName;
    private String buyerEmail;
    private String timeOfPurchase;
    private String dateOfPurchase;
    private int quantity;
    private int price;

    public TransactionDetails(){

    }

    public TransactionDetails(String productName, String buyerName, String buyerEmail, String timeOfPurchase, String dateOfPurchase, int quantity, int price) {
        this.productName = productName;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.timeOfPurchase = timeOfPurchase;
        this.dateOfPurchase = dateOfPurchase;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
