package academy.digitallab.store.shopping.domain;


import academy.digitallab.store.shopping.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "tbl_invoce_items")
public class InvoiceItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;
    @Positive(message = "El precio debe ser mayor que cero")
    private Double  price;

    @Positive(message = "la producto no  debe ser vacío")
    @Column(name = "product_id")
    private Long productId;


    @Transient
    private Double subTotal;

    @Transient
    private Product product;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    public InvoiceItem(){
        this.quantity=(double) 0;
        this.price=(double) 0;

    }
}
