package com.example.ex2.Repository;

import com.example.ex2.Model.Order;
import com.example.ex2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

//List<Product> findProductByUserId(Integer id);
Product findProductById(Integer productId);

    Product getProductById(Integer id);
}
