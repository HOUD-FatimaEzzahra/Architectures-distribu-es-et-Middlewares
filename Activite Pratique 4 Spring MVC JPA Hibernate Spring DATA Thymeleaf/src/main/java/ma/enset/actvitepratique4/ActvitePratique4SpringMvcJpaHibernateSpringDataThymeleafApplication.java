package ma.enset.actvitepratique4;

import ma.enset.actvitepratique4.entities.Patient;
import ma.enset.actvitepratique4.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class ActvitePratique4SpringMvcJpaHibernateSpringDataThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActvitePratique4SpringMvcJpaHibernateSpringDataThymeleafApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(
                    new Patient(1, "ALAMI", "Hiba", new Date(), true, 1)
            );
            patientRepository.save(
                    new Patient(2, "JALAL", "Ihssan", new Date(), false, 10)
            );
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getNom() + " " + p.getPrenom());
            });
        };
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
