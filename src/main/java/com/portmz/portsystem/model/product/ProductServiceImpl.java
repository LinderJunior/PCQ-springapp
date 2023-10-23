package com.portmz.portsystem.model.product;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new BusinesException("Product with ID " + id + " not found"));
    }

    @Override
    public ProductDto save(ProductDto dto) {
        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }

        Product product = new Product();

        copyDtoToEntityInsert(product, dto);
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product update(Long id, ProductDto dto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            copyDtoToEntityInsert(product, dto);
            return productRepository.save(product);
        }
        return null;
    }

    private void copyDtoToEntityInsert(Product product, ProductDto dto) {
        product.setName(dto.getName());
        product.setType(dto.getType());
    }
}
