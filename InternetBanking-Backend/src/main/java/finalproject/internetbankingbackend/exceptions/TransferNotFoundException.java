package finalproject.internetbankingbackend.exceptions;

public class TransferNotFoundException extends Exception {
    public TransferNotFoundException(String message) {
        super(message);
    }
}
