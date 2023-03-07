package ma.enset.gestiondespatients.web;

import lombok.AllArgsConstructor;
import ma.enset.gestiondespatients.entities.Patient;
import ma.enset.gestiondespatients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0")int page,
                           @RequestParam(name = "size", defaultValue = "5")int size,
                           @RequestParam(name = "motCle", defaultValue = "")String motCle){
        Page<Patient> pagePatients=patientRepository.findByNomContains(motCle,PageRequest.of(page,size));
        model.addAttribute("ListPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("motCle",motCle);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id, String motCle, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&motCle="+motCle;
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    @GetMapping("/formulairePatient")
    public String formulairePatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formulairePatient";
    }
}
