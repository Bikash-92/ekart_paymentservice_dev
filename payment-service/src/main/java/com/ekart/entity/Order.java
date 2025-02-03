package com.ekart.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_request")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "invoice_no")
    private String invoice;
    @OneToMany(mappedBy = "orderNumber",cascade = CascadeType.ALL)
    private List<Item> items;
    @Column(name = "status")
    private String status;
    @Column(name = "total_amount")
    private double totalAmount;
    @OneToOne(cascade = CascadeType.ALL) // Cascade operations
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToMany(mappedBy = "id")
    private ShippingAddress shippingAddress;


    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "invoice='" + invoice + '\'' +
                ", orderId=" + orderId +
                ", items=" + items +
                ", status='" + status + '\'' +
                ", totalAmount=" + totalAmount +
                ", shippingAddress=" + shippingAddress +
                '}';
    }
}
