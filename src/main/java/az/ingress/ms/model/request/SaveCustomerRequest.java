package az.ingress.ms.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveCustomerRequest {
    private String username;
    private int age;
    private String birthPlace;
    private LocalDate birthDate;

}
