package finalproject.internetbankingbackend.service;

import finalproject.internetbankingbackend.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Optional<Customer> findById(Integer id);
    Customer createCustomer(Customer customer);
}
