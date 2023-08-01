package az.ingress.ms.controller;

import az.ingress.ms.model.request.SaveAddressRequest;
import az.ingress.ms.model.request.UpdateAddressRequest;
import az.ingress.ms.model.response.AddressResponse;
import az.ingress.ms.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @ResponseStatus(OK)
    public List<AddressResponse> getAllAddress(){
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public AddressResponse getAddressById(@PathVariable Long id){
        return addressService.getAddressById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveAddress(@RequestBody SaveAddressRequest request){
        addressService.createAddress(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateAddress(@PathVariable Long id, @RequestBody UpdateAddressRequest addressRequest){
        addressService.updateAddress(id, addressRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
    }
}
