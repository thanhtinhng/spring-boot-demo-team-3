package demo.service;

import demo.entity.Account;
import demo.entity.Transaction;
import demo.repository.AccountRepository;
import demo.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Ghi log chuyển tiền: chạy ở transaction riêng
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransaction(Long fromAccountId, Long toAccountId, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new EntityNotFoundException("From Account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new EntityNotFoundException("To Account not found"));

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
        System.out.println("✅ Transaction logged");
    }
}
