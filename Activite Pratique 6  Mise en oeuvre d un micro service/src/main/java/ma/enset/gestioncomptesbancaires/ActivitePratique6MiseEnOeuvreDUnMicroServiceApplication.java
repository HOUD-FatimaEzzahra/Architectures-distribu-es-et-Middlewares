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
            bankAccountRepository.save(new BankAccount(null,new Date(),1000,"DHS", AccountType.CURRENT));
            bankAccountRepository.save(new BankAccount(null,new Date(),2000,"DHS", AccountType.SAVING));
            bankAccountRepository.save(new BankAccount(null,new Date(),3000,"DHS", AccountType.CURRENT));
            bankAccountRepository.save(new BankAccount(null,new Date(),4000,"DHS", AccountType.SAVING));
            bankAccountRepository.findAll().forEach(System.out::println);
        };
    }

}
