package org.example.DAO.repository;

import org.example.DAO.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.price > ?1")
    List<Product> findProductsByPriceGreaterThan(Double price);
}
