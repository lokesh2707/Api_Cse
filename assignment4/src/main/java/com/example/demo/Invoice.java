package com.example.demo;

import java.time.LocalDate;

public class Invoice {
    private int id;
    private String description;
    private String packing;
    private int qty;
    private double gst;
    private double rate;
    private double discount;
    private LocalDate expiryDate;   // ✅ use LocalDate, not String
    private double total;

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPacking() {
        return packing;
    }
    public void setPacking(String packing) {
        this.packing = packing;
    }

    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getGst() {
        return gst;
    }
    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
