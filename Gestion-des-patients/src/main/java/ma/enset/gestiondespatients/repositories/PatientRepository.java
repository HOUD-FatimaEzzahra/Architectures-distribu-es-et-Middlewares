package ma.enset.gestiondespatients.repositories;

import ma.enset.gestiondespatients.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
