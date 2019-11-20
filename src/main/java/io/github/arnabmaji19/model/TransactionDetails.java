package io.github.arnabmaji19.model;

//POJO for creating Transactions table
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

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(String timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
