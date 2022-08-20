package academy.digitallab.store.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product  implements Serializable {

    private Long id;
    private String numberProduct;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String status;
    private Category category;
}
