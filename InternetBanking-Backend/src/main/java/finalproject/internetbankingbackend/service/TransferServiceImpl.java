package finalproject.internetbankingbackend.service;

import finalproject.internetbankingbackend.entity.Customer;
import finalproject.internetbankingbackend.entity.Transfer;
import finalproject.internetbankingbackend.entity.TransferStatus;
import finalproject.internetbankingbackend.exceptions.CustomerNotFoundException;
import finalproject.internetbankingbackend.exceptions.ExpiredTransferException;
import finalproject.internetbankingbackend.exceptions.InsufficientFundsException;
import finalproject.internetbankingbackend.exceptions.TransferNotFoundException;
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
    public void initiateTransfer(String senderIban, String receiverIBAN, Double amount) throws CustomerNotFoundException, InsufficientFundsException {
        Optional<Customer> optionalSender = this.customerRepository.findByIban(senderIban);
        if (optionalSender.isPresent()) {
            Customer sender = optionalSender.get();
            if (sender.getSold() >= amount) {
                Optional<Customer> optionalReceiver = this.customerRepository.findByIban(receiverIBAN);
                if (optionalReceiver.isPresent()) {
                    sender.setSold(sender.getSold() - amount);
                    this.customerRepository.save(sender);
                    Transfer transfer = new Transfer(null, senderIban, receiverIBAN, amount, TransferStatus.PENDING);
                    this.transferRepository.save(transfer);
                } else {
                    throw new CustomerNotFoundException("Receiver not found");
                }
            } else {
                throw new InsufficientFundsException("You don't have enough funds to make the transfer");
            }
        } else {
            throw new CustomerNotFoundException("Sender not found");
        }
    }

    @Override
    public List<Transfer> getAllCustomerTransfers(String iban) {
        return this.transferRepository.findAllByReceiverIban(iban);
    }

    @Override
    public List<Transfer> getAllCustomerPendingTransfers(String iban) {
        return this.transferRepository.findAllPendingTransfers(iban);
    }

    @Override
    public Optional<Transfer> findTransferById(Integer id) {
        return this.transferRepository.findById(id);
    }

    @Override
    public void acceptTransfer(Integer transferId) throws TransferNotFoundException, ExpiredTransferException {
        Optional<Transfer> optionalTransfer = this.findTransferById(transferId);
        if (optionalTransfer.isPresent()) {
            Transfer transfer = optionalTransfer.get();
            if (transfer.getStatus() != TransferStatus.PENDING) {
                throw new ExpiredTransferException("This transfer has already been processed");
            }
            Customer receiver = this.customerRepository.findByIban(transfer.getReceiverIban()).get();
            receiver.setSold(receiver.getSold() + transfer.getAmount());
            this.customerRepository.save(receiver);
            transfer.setStatus(TransferStatus.ACCEPTED);
            this.transferRepository.save(transfer);
        } else {
            throw new TransferNotFoundException("Transfer not found with id: " + transferId);
        }
    }

    @Override
    public void declineTransfer(Integer transferId) throws TransferNotFoundException, ExpiredTransferException {
        Optional<Transfer> optionalTransfer = this.findTransferById(transferId);
        if (optionalTransfer.isPresent()) {
            Transfer transfer = optionalTransfer.get();
            if (transfer.getStatus() != TransferStatus.PENDING) {
                throw new ExpiredTransferException("This transfer has already been processed");
            }
            Customer sender = this.customerRepository.findByIban(transfer.getSenderIban()).get();
            sender.setSold(sender.getSold() + transfer.getAmount());
            this.customerRepository.save(sender);
            transfer.setStatus(TransferStatus.DECLINED);
            this.transferRepository.save(transfer);
        } else {
            throw new TransferNotFoundException("Transfer not found with id: " + transferId);
        }
    }
}
