package ma.enset.gestioncomptesbancaires.repositories;

import ma.enset.gestioncomptesbancaires.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
