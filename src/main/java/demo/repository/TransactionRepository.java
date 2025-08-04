package demo.repository;

import demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t " +
           "LEFT JOIN FETCH t.fromAccount fa " +
           "LEFT JOIN FETCH fa.user " +
           "LEFT JOIN FETCH t.toAccount ta " +
           "LEFT JOIN FETCH ta.user " +
           "WHERE fa.id = :accountId OR ta.id = :accountId")
    List<Transaction> findByAccountIdFetchAccountAndUser(Long accountId);
}