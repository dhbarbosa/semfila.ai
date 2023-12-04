package ai.semfila.api.DTO.Product;

import ai.semfila.api.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter @Setter
public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private String company;
    private String orders;
    private String type;

    public ProductResponse(Product product){
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        company = product.getCompany();
        orders = product.getOrders();
        type = product.getType();
    }
}
