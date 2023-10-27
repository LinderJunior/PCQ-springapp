package com.portmz.portsystem.model.product;

import com.portmz.portsystem.model.client.Client;
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

    private Long client_id;

    @Size(max = 60)
    private String type;

    private Client client;

    public ProductDto(Product entityProduct) {
        id = entityProduct.getId();
        name = entityProduct.getName();
        type = entityProduct.getType();
        client = entityProduct.getClient();
        client_id = entityProduct.getClient().getId();
    }
}
