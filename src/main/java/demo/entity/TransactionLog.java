package demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransactionLog {
	public TransactionLog() {}

    public TransactionLog(Long id, Long fromAccountId, Long toAccountId, double amount, LocalDateTime timestamp,
			Account account) {
		super();
		this.id = id;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
		this.timestamp = timestamp;
		this.account = account;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy load để demo N+1 nếu cần
    @JoinColumn(name = "account_id")
    private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public Long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

    // Getters/setters...
}
