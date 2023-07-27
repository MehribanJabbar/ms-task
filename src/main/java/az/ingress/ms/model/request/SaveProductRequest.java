package az.ingress.ms.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
