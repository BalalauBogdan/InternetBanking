package finalproject.internetbankingbackend.service;

import finalproject.internetbankingbackend.entity.Customer;
import finalproject.internetbankingbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public String generateIBAN() {
        String countryCode = "RO";
        int ibanLength = 24;
        StringBuilder iban = new StringBuilder(countryCode);
        Random random = new Random();
        for (int i = 0; i < ibanLength - 2; i++) {
            int digit = random.nextInt(10);
            iban.append(digit);
        }
        return iban.toString();
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return this.customerRepository.findByUsername(username);
    }
}
