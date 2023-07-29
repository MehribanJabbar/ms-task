package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.Address;
import az.ingress.ms.model.request.SaveAddressRequest;
import az.ingress.ms.model.request.UpdateAddressRequest;
import az.ingress.ms.model.response.AddressResponse;

public class AddressMapper {

    public static AddressResponse buildToResponse(Address address){
        return AddressResponse.builder()
                .id(address.getId())
                .city(address.getCity())
                .state(address.getState())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static Address buildToEntity(SaveAddressRequest request){
        return Address.builder()
                .city(request.getCity())
                .state(request.getState())
                .street(request.getStreet())
                .postalCode(request.getPostalCode())
                .build();
    }

    public static void updateToAddress(Address address, UpdateAddressRequest addressRequest){

    }
}
