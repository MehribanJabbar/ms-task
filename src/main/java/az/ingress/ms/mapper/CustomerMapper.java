package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.Customer;
import az.ingress.ms.model.request.SaveCustomerRequest;
import az.ingress.ms.model.request.UpdateCustomerRequest;
import az.ingress.ms.model.response.CustomerResponse;

public class CustomerMapper {
    public static CustomerResponse buildToResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .birthDate(customer.getBirthDate())
                .age(customer.getAge())
                .username(customer.getUsername())
                .birthPlace(customer.getBirthPlace())
                .build();
    }

    public static Customer buildToEntity(SaveCustomerRequest customerRequest){
        return Customer.builder()
                .age(customerRequest.getAge())
                .username(customerRequest.getUsername())
                .birthPlace(customerRequest.getBirthPlace())
                .birthDate(customerRequest.getBirthDate())
                .build();
    }

    public static void updateToCustomer(Customer customer, UpdateCustomerRequest customerRequest){

    }
}
