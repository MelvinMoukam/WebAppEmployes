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

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     * @return An iterable of all employees
     */

    public Iterable <Employe> getEmployees (){
        String baseApi= props.getApiUrl();
        String getEmployeesUrl = baseApi + "/employees";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Iterable<Employe>> response  = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employe>> () {}
        );

        log.debug("Get Employees call " + response.getStatusCode().toString());

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


}
