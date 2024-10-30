package com.example.internetbankingfrontend.entity;

public class Transaction {
    private String sender;
    private String recipient;
    private double amount;
    private int id;
    private String status;

    public Transaction(int id, String senderIban, String receiverIban, double amount, String status) {
        this.id = id;
        this.sender = senderIban;
        this.recipient = receiverIban;
        this.amount = amount;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
