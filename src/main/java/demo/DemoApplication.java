package demo;

import demo.entity.Account;
import demo.entity.User;
import demo.repository.AccountRepository;
import demo.repository.UserRepository;
import demo.controller.AccountController;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Tạo dữ liệu mẫu khi app khởi động
    @Bean
    CommandLineRunner initData(UserRepository userRepository, AccountRepository accountRepository) {
        return args -> {
            // Tạo User A và B
            User userA = new User(null, "Nguyen Van A");
            User userB = new User(null, "Tran Thi B");
            userRepository.save(userA);
            userRepository.save(userB);

            // Tạo account cho A và B
            Account accA = new Account(null, 1000.0, userA);
            Account accB = new Account(null, 500.0, userB);
            accountRepository.save(accA);
            accountRepository.save(accB);

            System.out.println("✅ Khởi tạo dữ liệu demo thành công:");
            System.out.println("User A ID = " + accA.getId() + ", Balance = " + accA.getBalance());
            System.out.println("User B ID = " + accB.getId() + ", Balance = " + accB.getBalance());
        };
    }
}
