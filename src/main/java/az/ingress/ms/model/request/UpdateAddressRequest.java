package az.ingress.ms.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {

    private String street;
    private String city;
    private String state;
    private String postalCode;
}
