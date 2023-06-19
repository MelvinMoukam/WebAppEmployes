package com.appemployes.webapp.service;

import com.appemployes.webapp.model.Employe;
import com.appemployes.webapp.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;


    public Employe getEmployee (final  int id) {
        return employeeProxy.getEmployee(id) ;
    }

    public Iterable<Employe> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final int id) {
        employeeProxy.deleteEmployee(id);;
    }

    public Employe saveEmployee (Employe employe){
            Employe savedEmployee;

            // Le nom de famille doit être mis en majuscule.
        employe.setLastName(employe.getLastName().toUpperCase());

        if (employe.getId() == null){

            //si l'id est null , cest un nouvel employé
            savedEmployee = employeeProxy.createEmployee(employe);

        }else {

            savedEmployee = employeeProxy.updateEmploye(employe);
        }
            return savedEmployee;
    }


}
