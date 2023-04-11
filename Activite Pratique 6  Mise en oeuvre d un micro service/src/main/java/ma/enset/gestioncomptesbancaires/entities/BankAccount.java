package ma.enset.gestioncomptesbancaires.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.gestioncomptesbancaires.enums.AccountType;
import org.hibernate.grammars.hql.HqlParser;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date creationDate;
    private double balance;
    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}
