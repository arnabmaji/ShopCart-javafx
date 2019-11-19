package io.github.arnabmaji19.model;

import org.bson.types.ObjectId;

public class CartItem {

    private ObjectId productId;
    private int quantity;

    public CartItem(){}

    public CartItem(ObjectId productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
