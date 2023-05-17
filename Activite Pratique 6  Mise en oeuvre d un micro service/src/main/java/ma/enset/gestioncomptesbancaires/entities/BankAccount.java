package ma.enset.gestioncomptesbancaires.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.gestioncomptesbancaires.enums.AccountType;
import org.hibernate.grammars.hql.HqlParser;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
    @Id
    private String id;
    private Date creationDate;
    private Double  balance;
    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}
