package demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Tự động tạo getter/setter/toString/hashCode/equals
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Giao dịch gửi tiền đi từ tài khoản này
    @OneToMany(mappedBy = "fromAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> sentTransactions = new ArrayList<>();

    // Giao dịch nhận tiền đến tài khoản này
    @OneToMany(mappedBy = "toAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions = new ArrayList<>();

    // ✅ Constructor khớp với new Account(null, balance, user)
    
 // ✅ Constructor khớp với new Account(null, balance, user)
    public Account() {
       
    }

    public Account(Long id, double balance, User user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    // ✅ Nếu  cần xử lý all transactions (gộp gửi + nhận)
    public List<Transaction> getAllTransactions() {
        List<Transaction> all = new ArrayList<>();
        all.addAll(sentTransactions);
        all.addAll(receivedTransactions);
        return all;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Transaction> getSentTransactions() {
		return sentTransactions;
	}

	public void setSentTransactions(List<Transaction> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}

	public List<Transaction> getReceivedTransactions() {
		return receivedTransactions;
	}

	public void setReceivedTransactions(List<Transaction> receivedTransactions) {
		this.receivedTransactions = receivedTransactions;
	}
}
