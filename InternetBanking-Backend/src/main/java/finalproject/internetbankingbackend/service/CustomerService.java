package finalproject.internetbankingbackend.service;

import finalproject.internetbankingbackend.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Optional<Customer> findById(Integer id);
    Customer createCustomer(Customer customer);
    String generateIBAN();
    Optional<Customer> findByUsername(String username);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    void addToSavingsAccount(String username, Double amount);
    void withdrawFromSavingsAccount(String username, Double amount);
    Double getSavingsAccountBalance(String username);
}
