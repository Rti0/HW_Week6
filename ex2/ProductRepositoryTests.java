package com.example.ex2;

import com.example.ex2.Model.MyUser;
import com.example.ex2.Model.Order;
import com.example.ex2.Model.Product;
import com.example.ex2.Repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;
    MyUser myUser;


    Product product1;
    List<Product> productList;


    @BeforeEach
    void setUp(){
        myUser=new MyUser(null,"Reem","1234","CUSTOMER",null);
        product1=new Product(null,"product1",100,null);

    }

@Test
    public void findProductById(){
        productRepository.save(product1);
        Product product=productRepository.getProductById(product1.getId());
    Assertions.assertThat(productList).isEqualTo(product1);
}

//@Test
//    public void getProductById(){
//        productRepository.save(product1);
//        Product product=productRepository.getProductById(product1.getId());
//        Assertions.assertThat(productList).isEqualTo(product);
//}
}
