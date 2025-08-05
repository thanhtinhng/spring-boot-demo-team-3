package org.example.service;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);

    Product findByName(String name);

    List<Product> findProductsByPriceGreaterThan(Double price);
}
