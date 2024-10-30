package finalproject.internetbankingbackend.service;

import finalproject.internetbankingbackend.entity.Transfer;

import java.util.List;

public interface TransferService {
    void initiateTransfer(Integer senderID, String receiverIBAN, Double amount);
    List<Transfer> getAllCustomerTransfers(Integer customerId);
}
