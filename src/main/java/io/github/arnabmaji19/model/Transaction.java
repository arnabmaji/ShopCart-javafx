package io.github.arnabmaji19.model;

import org.bson.types.ObjectId;

//POJO for adding Transaction history into database
public class Transaction {
    private ObjectId id;
    private ObjectId productId;
    private ObjectId userId;
    private String timeOfPurchase;
    private String dateOfPurchase;
    private int quantity;
    private int totalPrice;

    public Transaction(){}

    public Transaction(ObjectId id, ObjectId productId, ObjectId userId, String timeOfPurchase, String dateOfPurchase, int quantity, int totalPrice) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.timeOfPurchase = timeOfPurchase;
        this.dateOfPurchase = dateOfPurchase;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
