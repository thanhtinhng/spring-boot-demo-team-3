package demo.controller;


import demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/transfer")
    public String transfer(@RequestParam(name = "fromId") Long fromId,
                           @RequestParam(name = "toId") Long toId,
                           @RequestParam(name = "amount") double amount) {
        try {
            accountService.transferMoney(fromId, toId, amount);
            return "Transfer successful";
        } catch (Exception e) {
            return "Transfer failed: " + e.getMessage();
        }
    }
}
