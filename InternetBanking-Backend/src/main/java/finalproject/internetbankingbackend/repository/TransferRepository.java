package finalproject.internetbankingbackend.repository;

import finalproject.internetbankingbackend.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    @Query(value = "SELECT * FROM transfers WHERE receiver_id = :id", nativeQuery = true)
    List<Transfer> getTransfersByReceiverId(@Param("id") Integer id);
}
