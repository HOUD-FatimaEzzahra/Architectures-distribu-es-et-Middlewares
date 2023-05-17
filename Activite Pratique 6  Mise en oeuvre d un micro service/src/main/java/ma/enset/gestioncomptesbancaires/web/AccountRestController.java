package ma.enset.gestioncomptesbancaires.web;

import ma.enset.gestioncomptesbancaires.entities.BankAccount;
import ma.enset.gestioncomptesbancaires.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account not found",id)));
    }

    @PostMapping("/addAccount")
    public BankAccount save(BankAccount bankAccount){
        if (bankAccount.getId()==null)bankAccount.setId(UUID.randomUUID().toString() );
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/accounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if (account.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (account.getCreationDate()!=null)account.setCreationDate(bankAccount.getCreationDate());
        if (account.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());
        if (account.getType()!=null)account.setType(bankAccount.getType());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}

