package az.ingress.ms.controller;

import az.ingress.ms.model.request.SaveAddressRequest;
import az.ingress.ms.model.request.UpdateAddressRequest;
import az.ingress.ms.model.response.AddressResponse;
import az.ingress.ms.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressResponse> findAllAddress(){
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public AddressResponse findAddressById(@PathVariable Long id){
        return addressService.getAddressById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createAddress(@RequestBody SaveAddressRequest request){
        addressService.createAddress(request);
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
