package demo.service;

import demo.entity.Account;
import demo.repository.AccountRepository;
import demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionService transactionService;


    // Giao dịch tổng (bọc cả withdraw và deposit)
    @Transactional
    public void transferMoney(Long fromAccountId, Long toAccountId, double amount) {
        withdraw(fromAccountId, amount);

        // Nếu xảy ra lỗi ở đây, toàn bộ giao dịch (bao gồm cả withdraw) sẽ rollback
        if (amount > 1000) {
            throw new RuntimeException("Amount too large -> simulate rollback");
        }

        deposit(toAccountId, amount);
     // Log chuyển tiền -> REQUIRES_NEW nên không bị rollback theo cha
        transactionService.logTransaction(fromAccountId, toAccountId, amount);
    }

    // Rút tiền, cùng transaction với transferMoney
    public void withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Withdraw: Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        System.out.println("✅ Withdraw success: -" + amount);
    }

    // Nạp tiền, cùng transaction với transferMoney
    public void deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Deposit: Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        System.out.println("✅ Deposit success: +" + amount);
    }
}
