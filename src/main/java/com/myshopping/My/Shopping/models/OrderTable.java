package com.myshopping.My.Shopping.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
public class OrderTable {
    //particular order can have multiple products
    //order table vs product table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID orderId;
    int totalPrice;
    int totalQuantity;
    //Buyer is a User Only -> We need to maintain relationship between user table and order table
    //from order table point of view what kind of relationship it looks like
    @ManyToOne
    AppUser user;

    @OneToMany
            //becaus of one to many annotation another table will get created which
            //orderId vs producted
            //a particular order
    List<Product> products;
    String status;// Delivered in Transit,Returned,Cancelled
    String paymentMethod;//UPI , Debitcard,COD

    public OrderTable(UUID orderId, int totalPrice, int totalQuantity, AppUser user, List<Product> products, String status, String paymentMethod) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.user = user;
        this.products = products;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public OrderTable() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
