package com.portmz.portsystem.model.product;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @Size(max = 60)
    private String name;

    @Size(max = 60)
    private String type;

    public ProductDto(Product entityProduct) {
        id = entityProduct.getId();
        name = entityProduct.getName();
        type = entityProduct.getType();
    }
}
