package ma.enset.gestiondespatients.web;

import lombok.AllArgsConstructor;
import ma.enset.gestiondespatients.entities.Patient;
import ma.enset.gestiondespatients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String patients(Model model){
        List<Patient>patients=patientRepository.findAll();
        model.addAttribute("ListPatients",patients);
        return "patients";
    }



}
