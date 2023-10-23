package com.portmz.portsystem.model.product;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    Product getProductById(Long id);

    ProductDto save(ProductDto dto);

    void delete(Long id);

    Product update(Long id, ProductDto dto);
}
