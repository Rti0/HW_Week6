package com.example.ex2.Service;

import com.example.ex2.ApiException.ApiException;
import com.example.ex2.Model.Product;
import com.example.ex2.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


public List<Product> getAll(){
    return productRepository.findAll();
}


public void addProduct(Product product){
    productRepository.save(product);
}

public void updateProduct(Product product,Integer productId){
    Product product1=productRepository.findProductById(productId);
    if (product1==null){
        throw new ApiException("not found");
    }
    product1.setId(product.getId());
    product1.setName(product.getName());
    product1.setPrice(product.getPrice());
    productRepository.save(product1);
}

    public void deleteProduct(Integer productId){
        Product old=productRepository.findProductById(productId);
        if (old==null){
            throw new ApiException("Not found");
        }
       productRepository.delete(old);
    }

    public Product getProductById(Integer productId) {
        Product p= productRepository.getProductById(productId);
        if (p == null) {
            throw new ApiException("Not found");
        }
        return p;
    }
}
