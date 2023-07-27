package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.AddressEntity;
import az.ingress.ms.model.request.SaveAddressRequest;
import az.ingress.ms.model.request.UpdateAddressRequest;
import az.ingress.ms.model.response.AddressResponse;

public class AddressMapper {

    public static AddressResponse buildToResponse(AddressEntity address){
        return AddressResponse.builder()
                .id(address.getId())
                .city(address.getCity())
                .state(address.getState())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static AddressEntity buildToEntity(SaveAddressRequest addressRequest){
        return AddressEntity.builder()
                .state(addressRequest.getState())
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .postalCode(addressRequest.getPostalCode())
                .build();
    }

    public static void updateToAddress(AddressEntity address, UpdateAddressRequest addressRequest){

    }
}
