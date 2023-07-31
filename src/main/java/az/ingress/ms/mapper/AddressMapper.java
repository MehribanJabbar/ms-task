package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.AddressEntity;
import az.ingress.ms.model.request.SaveAddressRequest;
import az.ingress.ms.model.request.UpdateAddressRequest;
import az.ingress.ms.model.response.AddressResponse;

public class AddressMapper {

    public static AddressResponse buildToResponse(AddressEntity addressEntity){
        return AddressResponse.builder()
                .id(addressEntity.getId())
                .city(addressEntity.getCity())
                .state(addressEntity.getState())
                .street(addressEntity.getStreet())
                .postalCode(addressEntity.getPostalCode())
                .build();
    }

    public static AddressEntity buildToEntity(SaveAddressRequest request){
        return AddressEntity.builder()
                .city(request.getCity())
                .state(request.getState())
                .street(request.getStreet())
                .postalCode(request.getPostalCode())
                .build();
    }

    public static void updateToAddress(AddressEntity addressEntity, UpdateAddressRequest request){
        addressEntity.setCity(request.getCity());
        addressEntity.setStreet(request.getStreet());
        addressEntity.setPostalCode(request.getPostalCode());
        addressEntity.setState(request.getState());
    }
}
