package az.ingress.ms.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {

    private Long id;
    private String cvv;
    private String pan;
    private LocalDate expirationDate;
    private String cardsHolder;
}
