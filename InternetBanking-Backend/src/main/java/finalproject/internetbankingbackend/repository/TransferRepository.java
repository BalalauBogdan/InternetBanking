package finalproject.internetbankingbackend.repository;

import finalproject.internetbankingbackend.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    List<Transfer> findAllByReceiverIban(String iban);

    @Query(value = "SELECT * FROM transfers WHERE receiver_iban = :iban AND status = 'PENDING'", nativeQuery = true)
    List<Transfer> findAllPendingTransfers(@Param("iban") String iban);
}
