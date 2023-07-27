package az.ingress.ms.service;

import az.ingress.ms.dao.entity.CustomerEntity;
import az.ingress.ms.dao.repository.CustomerRepository;
import az.ingress.ms.exception.NotFoundException;
import az.ingress.ms.mapper.CustomerMapper;
import az.ingress.ms.model.enums.Status;
import az.ingress.ms.model.request.SaveCustomerRequest;
import az.ingress.ms.model.request.UpdateCustomerRequest;
import az.ingress.ms.model.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.ms.mapper.CustomerMapper.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void customerServiceRunning(){
        System.out.println("Customer service is running");
    }
    public List<CustomerResponse> getAllCustomers(){
        List<CustomerResponse> customers = customerRepository.findAll()
                .stream().map(CustomerMapper::buildToResponse)
                .collect(Collectors.toList());

        return customers;
    }

    public CustomerResponse getCustomerById(Long id){
        var customer=fetchCustomerIfExist(id);
        return buildToResponse(customer);
    }

    public void updateCustomer(Long id, UpdateCustomerRequest customerRequest){
        var customer = fetchCustomerIfExist(id);
        updateToCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    public void createCustomer(SaveCustomerRequest customerRequest){
        customerRepository.save(buildToEntity(customerRequest));
    }

    public void deleteCustomer(Long id){
        var customer = fetchCustomerIfExist(id);
        customer.setStatus(Status.DELETED);
        customerRepository.save(customer);
    }

    public CustomerEntity fetchCustomerIfExist(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("CUSTOMER_NOT_FOUND"));

    }
}
