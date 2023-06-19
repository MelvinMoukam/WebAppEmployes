package com.appemployes.webapp.controller;


import com.appemployes.webapp.model.Employe;
import com.appemployes.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String home (Model model ){

        Iterable<Employe> listEmployees = service.getEmployees();
        model.addAttribute("employees" , listEmployees);
        return "Home";
    }

    @GetMapping ("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final int id){
        service.deleteEmployee(id);
        return new ModelAndView("redirect :/");
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employe employe){
        if(employe.getId()!=null){
            // L'employé du formulaire de mise à jour a le champ mot de passe non rempli,
            // nous le remplissons donc avec le mot de passe actuel.
            Employe current =service.getEmployee(employe.getId());
            employe.setPassword(current.getPassword());
        }
        service.saveEmployee(employe);
        return  new ModelAndView("redirect : /");
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model){
        Employe e =new Employe();
        model.addAttribute("employee", e);
        return "FormNewEmployee";

    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmloyee(@PathVariable("id") final int id, Model model){
        Employe e = service.getEmployee(id);
        model.addAttribute("employee", e);
        return "FormUpdateEmployee";
    }
}
