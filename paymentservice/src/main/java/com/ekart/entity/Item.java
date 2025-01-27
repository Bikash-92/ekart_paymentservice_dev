package com.ekart.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    @Column(name = "name")
    private String nm;
    @Column(name = "unit")
    private int count;
    @Column(name = "amount")
    private double amount;
    @ManyToOne
    @JoinColumn(name = "order_number", referencedColumnName = "order_id")
    private Order orderNumber;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public Order getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Order orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Item{" +
                "amount=" + amount +
                ", itemId=" + itemId +
                ", nm='" + nm + '\'' +
                ", count=" + count +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
