package finalproject.internetbankingbackend.exceptions;

public class ExpiredTransferException extends Exception {
    public ExpiredTransferException(String message) {
        super(message);
    }
}
