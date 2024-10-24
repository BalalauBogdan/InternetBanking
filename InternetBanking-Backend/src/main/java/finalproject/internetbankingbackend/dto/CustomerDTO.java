package finalproject.internetbankingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
}
