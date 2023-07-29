package az.ingress.ms.service;

import az.ingress.ms.dao.entity.Address;
import az.ingress.ms.dao.repository.AddressRepository;
import az.ingress.ms.exception.NotFoundException;
import az.ingress.ms.mapper.AddressMapper;
import az.ingress.ms.model.request.SaveAddressRequest;
import az.ingress.ms.model.request.UpdateAddressRequest;
import az.ingress.ms.model.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.ms.mapper.AddressMapper.*;
import static az.ingress.ms.model.enums.Status.DELETED;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public List<AddressResponse> getAllAdress(){
        List<AddressResponse> addressResponses = addressRepository.findAll()
                .stream().map(AddressMapper::buildToResponse)
                .collect(Collectors.toList());
        return addressResponses;
    }

    public AddressResponse getAddressById(Long id){
        var address= fetchAddressIfExist(id);
        return buildToResponse(address);
    }

    public void createAddress(SaveAddressRequest request){
        addressRepository.save(buildToEntity(request));
    }

    public void updateAddress(Long id, UpdateAddressRequest addressRequest){
        var address = fetchAddressIfExist(id);
        updateToAddress(address, addressRequest);
        addressRepository.save(address);
    }

    public void deleteAddress(Long id){
        var address = fetchAddressIfExist(id);
        address.setStatus(DELETED);
        addressRepository.save(address);
    }

    public Address fetchAddressIfExist(Long id){
        return addressRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("ADDRESS_NOT_FOUND"));
    }
}
