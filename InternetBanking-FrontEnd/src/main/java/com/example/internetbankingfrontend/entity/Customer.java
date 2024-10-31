package com.example.internetbankingfrontend.entity;

public class Customer {
    private Integer id;
    private String iban;
    private String role;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private double amount;
    private double savingsSold;

    public Customer(){}
    public Customer(Integer id, String iban, String role, String username, String password, String firstname, String lastname, String phoneNumber, double amount, double savingsSold) {
        this.id = id;
        this.iban = iban;
        this.role = role;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.amount=amount;
        this.savingsSold=savingsSold;
    }

    public double getSavingsSold() {
        return savingsSold;
    }

    public void setSavingsSold(double savingsSold) {
        this.savingsSold = savingsSold;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}
