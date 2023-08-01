package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.CustomerEntity;
import az.ingress.ms.model.request.SaveCustomerRequest;
import az.ingress.ms.model.request.UpdateCustomerRequest;
import az.ingress.ms.model.response.CustomerResponse;

public class CustomerMapper {
    public static CustomerResponse buildToResponse(CustomerEntity customerEntity){
        return CustomerResponse.builder()
                .id(customerEntity.getId())
                .birthDate(customerEntity.getBirthDate())
                .age(customerEntity.getAge())
                .username(customerEntity.getUsername())
                .birthPlace(customerEntity.getBirthPlace())
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

    public static void updateToCustomer(CustomerEntity customerEntity, UpdateCustomerRequest request){
        customerEntity.setUsername(request.getUsername());
        customerEntity.setBirthPlace(request.getBirthPlace());
        customerEntity.setBirthDate(request.getBirthDate());
        customerEntity.setAge(request.getAge());
    }
}
