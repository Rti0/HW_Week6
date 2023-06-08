package com.example.ex2;

import com.example.ex2.Model.MyUser;
import com.example.ex2.Model.Product;
import com.example.ex2.Repository.MyUserRepository;
import com.example.ex2.Repository.OrderRepository;
import com.example.ex2.Repository.ProductRepository;
import com.example.ex2.Service.OrderService;
import com.example.ex2.Service.ProductService;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @InjectMocks
    ProductService productService;



    @Mock
    ProductRepository productRepository;


    MyUser myUser;


Product product1,product2,product3;
List<Product>products;


    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"Reem","1234","CUSTOMER",null);
        product1=new Product(null,"product1",100,null);
        product2=new Product(null,"product2",100,null);
        product3=new Product(null,"product3",100,null);


        products=new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    @Test
    public void getAll(){
        when(productRepository.findAll()).thenReturn(products);
        List<Product>productList=productService.getAll();
        Assertions.assertEquals(3,productList.size());
        verify(productRepository,times(1)).findAll();
    }

    @Test
    public void addProduct(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.addProduct(product2);
        verify(productRepository,times(1)).save(product1);
    }
@Test
public void updateProduct(){
    when(productRepository.findProductById(product1.getId())).thenReturn(product1);
            verify(productRepository,times(1)).getProductById(product1.getId());
    verify(productRepository,times(1)).save(product1);
}
@Test
public void deleteProduct(){
        when(productRepository.getProductById(product1.getId())).thenReturn(product1);
        productService.deleteProduct(product1.getId());
    verify(productRepository,times(1)).getProductById(product1.getId());
    verify(productRepository,times(1)).delete(product1);
}

    @Test
    public void getProductById() {
        when(productRepository.getProductById(product1.getId())).thenReturn(product1);
        productService.getProductById(product1.getId());
        Assertions.assertEquals(products.get(0), myUser.getId());
        verify(productRepository, times(1)).getProductById(product1.getId());
    }

}
