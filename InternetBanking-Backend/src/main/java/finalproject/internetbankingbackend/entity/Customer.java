package finalproject.internetbankingbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double sold;
    private String iban;
    private String role;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Transfer> sentTransfers;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Transfer> receivedTransfers;
}
