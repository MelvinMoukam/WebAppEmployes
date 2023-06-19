package com.appemployes.webapp.repository;

import com.appemployes.webapp.CustomProperties;
import com.appemployes.webapp.model.Employe;
import org.springframework.core.ParameterizedTypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j // facilite la journalisation
@Component
public class EmployeeProxy {

    @Autowired
    private  CustomProperties props;

    /**
     * Get all employees
     * @return An iterable of all employees
     */

    public Iterable <Employe> getEmployees(){
        String baseApi= props.getApiUrl();
        String getEmployeesUrl = baseApi + "/employees";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Iterable<Employe>> response  = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employe>> () {}
        );
        // enregistre une information de débogage sur l'appel à la méthode.
        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an employee by the id
     * @param id The id of the employee
     * @return The employee which matches the id
     */
    public Employe getEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employe> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employe.class
        );

        log.debug("Get Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new employee
     * @param e A new employee (without an id)
     * @return The employee full filled (with an id)
     */


    public Employe createEmployee(Employe e){
        String baseApi= props.getApiUrl();
        String createEmployeesUrl = baseApi + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employe> request = new HttpEntity<Employe>(e);
        ResponseEntity<Employe> response = restTemplate.exchange(
                createEmployeesUrl,
                HttpMethod.POST,
                request,
                Employe.class

        );

        log.debug( "create Employee call " +response.getStatusCode().toString());

        return response.getBody();

    }

    /**
     * Update an employee - using the PUT HTTP Method.
     * @param e Existing employee to update
     */

    public Employe updateEmploye (Employe e){
        String baseApiUrl = props.getApiUrl();
        String updateEmployeUrl = baseApiUrl + "/employee" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employe> request = new HttpEntity<Employe>(e);
        ResponseEntity<Employe> response = restTemplate.exchange(
                updateEmployeUrl,
                HttpMethod.PUT,
                request,
                Employe.class

        );

        log.debug( "update Employee call " +response.getStatusCode().toString());

        return response.getBody();

    }


    public void deleteEmployee (int id){

        String baseApiUrl= props.getApiUrl();
        String deleteEmployeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Void> response = restTemplate.exchange(
                deleteEmployeUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug( "Delete Employee call " + ((ResponseEntity<Void>) response).getStatusCode().toString());

    }

}
