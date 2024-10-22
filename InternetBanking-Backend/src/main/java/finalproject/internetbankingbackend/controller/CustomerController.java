package finalproject.internetbankingbackend.controller;

import finalproject.internetbankingbackend.dto.CustomerDTO;
import finalproject.internetbankingbackend.entity.Customer;
import finalproject.internetbankingbackend.service.CustomerService;
import finalproject.internetbankingbackend.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<Customer> customerList = this.customerService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("List with all customers")
                .data(customerList)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> optionalCustomer = this.customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("Customer with id: " + id + " has been found")
                    .data(optionalCustomer.get())
                    .build();
            return ResponseEntity.ok(response);
        }
        ApiResponse response = new ApiResponse.Builder()
                .status(404)
                .message("Customer with id: " + id + " not found")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setIban(customerDTO.getIban());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Customer created successfully")
                .data(this.customerService.createCustomer(customer))
                .build();
        return ResponseEntity.ok(response);
    }
}