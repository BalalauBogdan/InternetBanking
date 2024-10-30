package finalproject.internetbankingbackend.service;

import finalproject.internetbankingbackend.entity.Customer;
import finalproject.internetbankingbackend.entity.Transfer;
import finalproject.internetbankingbackend.entity.TransferStatus;
import finalproject.internetbankingbackend.repository.CustomerRepository;
import finalproject.internetbankingbackend.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;
    private CustomerRepository customerRepository;

    public TransferServiceImpl(TransferRepository transferRepository, CustomerRepository customerRepository) {
        this.transferRepository = transferRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void initiateTransfer(Integer senderID, String receiverIBAN, Double amount) {
        Optional<Customer> optionalSender = this.customerRepository.findById(senderID);
        if (optionalSender.isPresent()) {
            Customer sender = optionalSender.get();
            if (sender.getSold() >= amount) {
                Optional<Customer> optionalReceiver = this.customerRepository.findByIban(receiverIBAN);
                if (optionalReceiver.isPresent()) {
                    Customer receiver = optionalReceiver.get();
                    Transfer transfer = new Transfer(null, sender, receiver, amount, TransferStatus.PENDING);
                    Transfer dbTransfer = this.transferRepository.save(transfer);
                }
            }
        }
    }

    @Override
    public List<Transfer> getAllCustomerTransfers(Integer customerId) {
        return this.transferRepository.getTransfersByReceiverId(customerId);
    }
}
