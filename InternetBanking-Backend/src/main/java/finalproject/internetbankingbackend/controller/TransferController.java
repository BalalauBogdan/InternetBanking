package finalproject.internetbankingbackend.controller;

import finalproject.internetbankingbackend.dto.TransferDTO;
import finalproject.internetbankingbackend.service.TransferService;
import finalproject.internetbankingbackend.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
@CrossOrigin
public class TransferController {
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<ApiResponse> initiateTransfer(@RequestBody TransferDTO transferDTO) {
        this.transferService.initiateTransfer(transferDTO.getSenderId(), transferDTO.getReceiverIBAN(), transferDTO.getAmount());
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Transfer")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse> getAllCustomerTransfers(@PathVariable Integer customerId) {
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("All transfers from customer with id: " + customerId)
                .data(this.transferService.getAllCustomerTransfers(customerId))
                .build();
        return ResponseEntity.ok(response);
    }
}
