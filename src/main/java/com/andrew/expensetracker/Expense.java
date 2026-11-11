package com.andrew.expensetracker;

public class Expense {

    private int id;
    private String description;
    private double amount;
    private int categoryId;

    public Expense(int id, String description, double amount, int categoryId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", categoryId=" + categoryId +
                '}';
    }

}
