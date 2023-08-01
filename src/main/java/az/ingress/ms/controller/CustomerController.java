package az.ingress.ms.controller;

import az.ingress.ms.model.request.SaveCustomerRequest;
import az.ingress.ms.model.request.UpdateCustomerRequest;
import az.ingress.ms.model.response.CustomerResponse;
import az.ingress.ms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/customers")
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping
    public List<CustomerResponse> findAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerResponse getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest customerRequest){
        customerService.updateCustomer(id, customerRequest);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCustomer(@RequestBody SaveCustomerRequest customerRequest){
        customerService.createCustomer(customerRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}
