package com.ekart.dto;


public class Item {
    private int itemId;
    private String nm;
    private int count;
    private double amount;
    private Order orderId;

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

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "amount=" + amount +
                ", itemId=" + itemId +
                ", nm='" + nm + '\'' +
                ", count=" + count +
                ", orderId=" + orderId +
                '}';
    }
}
