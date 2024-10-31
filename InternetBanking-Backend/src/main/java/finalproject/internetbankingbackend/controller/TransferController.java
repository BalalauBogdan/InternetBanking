package finalproject.internetbankingbackend.controller;

import finalproject.internetbankingbackend.dto.TransferDTO;
import finalproject.internetbankingbackend.exceptions.CustomerNotFoundException;
import finalproject.internetbankingbackend.exceptions.ExpiredTransferException;
import finalproject.internetbankingbackend.exceptions.InsufficientFundsException;
import finalproject.internetbankingbackend.exceptions.TransferNotFoundException;
import finalproject.internetbankingbackend.service.TransferService;
import finalproject.internetbankingbackend.utils.ApiResponse;
import org.springframework.http.HttpStatus;
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
        try {
            this.transferService.initiateTransfer(transferDTO.getSenderIban(), transferDTO.getReceiverIban(), transferDTO.getAmount());
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("Transfer made successfully")
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        } catch (CustomerNotFoundException | InsufficientFundsException e) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(404)
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/all/{customerIban}")
    public ResponseEntity<ApiResponse> getAllCustomerTransfers(@PathVariable String customerIban) {
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("List with all customer transfers")
                .data(this.transferService.getAllCustomerTransfers(customerIban))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pending/{customerIban}")
    public ResponseEntity<ApiResponse> getAllCustomerPendingTransfers(@PathVariable String customerIban) {
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("List with all customer pending transfers")
                .data(this.transferService.getAllCustomerPendingTransfers(customerIban))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/accept/{transferId}")
    public ResponseEntity<ApiResponse> acceptTransfer(@PathVariable Integer transferId) {
        try {
            this.transferService.acceptTransfer(transferId);
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("Transfer accepted successfully")
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        } catch (TransferNotFoundException | ExpiredTransferException e) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(404)
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/decline/{transferId}")
    public ResponseEntity<ApiResponse> declineTransfer(@PathVariable Integer transferId) {
        try {
            this.transferService.declineTransfer(transferId);
            ApiResponse response = new ApiResponse.Builder()
                    .status(200)
                    .message("Transfer declined successfully")
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        } catch (TransferNotFoundException | ExpiredTransferException e) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(404)
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
