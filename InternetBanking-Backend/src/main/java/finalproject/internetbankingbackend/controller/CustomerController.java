package finalproject.internetbankingbackend.controller;

import finalproject.internetbankingbackend.dto.*;
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
        Optional<Customer> optionalCustomer = this.customerService.findByUsername(customerDTO.getUsername());

        if (optionalCustomer.isPresent()) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(404)
                    .message("Customer already exists")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Customer customer = new Customer();
        customer.setIban(this.customerService.generateIBAN());
        customer.setSold(0.0);
        customer.setRole("USER");
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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginCustomer(@RequestBody LoginDTO loginDTO) {
        Optional<Customer> optionalCustomer = this.customerService.findByUsername(loginDTO.getUsername());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customer.getPassword().equals(loginDTO.getPassword())) {
                ApiResponse response = new ApiResponse.Builder()
                        .status(200)
                        .message("Login successfully")
                        .data(customer)
                        .build();
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse response = new ApiResponse.Builder()
                .status(404)
                .message("Login unsuccessfully")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> depositMoney(@RequestBody DepositDTO depositDTO) {
        Optional<Customer> optionalCustomer = this.customerService.findByUsername(depositDTO.getUsername());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setSold(customer.getSold() + depositDTO.getAmount());
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("Deposit made successfully")
                    .data(this.customerService.updateCustomer(customer))
                    .build();
            return ResponseEntity.ok(response);
        }
        ApiResponse response = new ApiResponse.Builder()
                .status(404)
                .message("User not found")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdrawMoney(@RequestBody WithdrawDTO withdrawDTO) {
        Optional<Customer> optionalCustomer = this.customerService.findByUsername(withdrawDTO.getUsername());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (withdrawDTO.getAmount() > customer.getSold()) {
                ApiResponse response = new ApiResponse.Builder()
                        .status(400)
                        .message("Insufficient funds")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else {
                customer.setSold(customer.getSold() - withdrawDTO.getAmount());
                ApiResponse response = new ApiResponse.Builder()
                        .status(200)
                        .message("Withdraw made successfully")
                        .data(this.customerService.updateCustomer(customer))
                        .build();
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse response = new ApiResponse.Builder()
                .status(404)
                .message("User not found")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/sold")
    public ResponseEntity<ApiResponse> displaySold(@RequestBody SoldDTO soldDTO) {
        Optional<Customer> optionalCustomer = this.customerService.findByUsername(soldDTO.getUsername());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("Sold")
                    .data(customer.getSold())
                    .build();
            return ResponseEntity.ok(response);
        }
        ApiResponse response = new ApiResponse.Builder()
                .status(404)
                .message("User not found")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
