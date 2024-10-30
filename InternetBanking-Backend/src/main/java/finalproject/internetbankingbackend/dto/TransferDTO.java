package finalproject.internetbankingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {
    private Integer senderId;
    private String receiverIBAN;
    private Double amount;
}
