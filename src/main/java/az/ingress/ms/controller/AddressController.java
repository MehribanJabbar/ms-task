package az.ingress.ms.controller;

import az.ingress.ms.model.request.SaveAddressRequest;
import az.ingress.ms.model.request.UpdateAddressRequest;
import az.ingress.ms.model.response.AddressResponse;
import az.ingress.ms.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressResponse> findAllAddress(){
        return addressService.getAllAdress();
    }

    @GetMapping("/{id}")
    public AddressResponse findAddressById(@PathVariable Long id){
        return addressService.getAddressById(id);
    }

    @PostMapping
    public void createAddress(@RequestBody SaveAddressRequest addressRequest){
        addressService.createAddress(addressRequest);
    }

    @PutMapping("/{id}")
    public void updateAddress(@PathVariable Long id, @RequestBody UpdateAddressRequest addressRequest){
        addressService.updateAddress(id, addressRequest);
    }

    @DeleteMapping
    public void deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
    }
}
