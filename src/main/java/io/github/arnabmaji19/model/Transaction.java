package io.github.arnabmaji19.model;

import org.bson.types.ObjectId;

public class Transaction {
    private ObjectId id;
    private ObjectId productId;
    private String buyerName;
    private String buyerEmail;
    private String timeOfPurchase;
    private String dateOfPurchase;


    public Transaction(ObjectId id, ObjectId productId, String buyerName, String buyerEmail, String timeOfPurchase, String dateOfPurchase) {
        this.id = id;
        this.productId = productId;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.timeOfPurchase = timeOfPurchase;
        this.dateOfPurchase = dateOfPurchase;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
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
}
