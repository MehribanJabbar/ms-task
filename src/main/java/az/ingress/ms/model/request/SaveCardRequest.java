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
public class SaveCardRequest {
    private String cvv;
    private String pan;
    private LocalDate expirationDate;
    private String cardsHolder;
}
