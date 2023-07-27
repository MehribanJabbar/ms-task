package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.CustomerEntity;
import az.ingress.ms.model.request.SaveCustomerRequest;
import az.ingress.ms.model.request.UpdateCustomerRequest;
import az.ingress.ms.model.response.CustomerResponse;

public class CustomerMapper {
    public static CustomerResponse buildToResponse(CustomerEntity customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .birthDate(customer.getBirthDate())
                .age(customer.getAge())
                .username(customer.getUsername())
                .birthPlace(customer.getBirthPlace())
                .build();
    }

    public static CustomerEntity buildToEntity(SaveCustomerRequest customerRequest){
        return CustomerEntity.builder()
                .age(customerRequest.getAge())
                .username(customerRequest.getUsername())
                .birthPlace(customerRequest.getBirthPlace())
                .birthDate(customerRequest.getBirthDate())
                .build();
    }

    public static void updateToCustomer(CustomerEntity customer, UpdateCustomerRequest customerRequest){

    }
}
