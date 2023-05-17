package ma.enset.gestioncomptesbancaires;

import ma.enset.gestioncomptesbancaires.entities.BankAccount;
import ma.enset.gestioncomptesbancaires.enums.AccountType;
import ma.enset.gestioncomptesbancaires.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ActivitePratique6MiseEnOeuvreDUnMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitePratique6MiseEnOeuvreDUnMicroServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository){
        return args -> {
            for (int i = 0; i < 10; i++) {
                bankAccountRepository.save(BankAccount.builder()
                        .id("id"+i)
                        .balance(1000.0)
                        .creationDate(new Date())
                        .currency("MAD")
                        .type(AccountType.CURRENT)
                        .build());
            }
            for (int i = 0; i < 10; i++) {
                bankAccountRepository.save(BankAccount.builder()
                        .id("id"+i)
                        .balance(1000.0)
                        .creationDate(new Date())
                        .currency("MAD")
                        .type(AccountType.SAVING)
                        .build());
            }
        };
    }

}
