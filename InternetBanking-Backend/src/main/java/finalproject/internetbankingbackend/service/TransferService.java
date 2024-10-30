package finalproject.internetbankingbackend.service;

import finalproject.internetbankingbackend.entity.Transfer;
import finalproject.internetbankingbackend.exceptions.CustomerNotFoundException;
import finalproject.internetbankingbackend.exceptions.ExpiredTransferException;
import finalproject.internetbankingbackend.exceptions.InsufficientFundsException;
import finalproject.internetbankingbackend.exceptions.TransferNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TransferService {
    void initiateTransfer(String senderIban, String receiverIban, Double amount) throws CustomerNotFoundException, InsufficientFundsException;
    List<Transfer> getAllCustomerTransfers(String iban);
    List<Transfer> getAllCustomerPendingTransfers(String iban);
    Optional<Transfer> findTransferById(Integer id);
    void acceptTransfer(Integer transferId) throws TransferNotFoundException, ExpiredTransferException;
    void declineTransfer(Integer transferId) throws TransferNotFoundException, ExpiredTransferException;
}
