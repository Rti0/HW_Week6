package com.example.ex2.Controller;

import com.example.ex2.ApiResponse.ApiResponse;
import com.example.ex2.Model.Product;
import com.example.ex2.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Product> products=productService.getAll();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Product product){
      productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("Done"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody Product product, @PathVariable Integer productId){
      productService.updateProduct(product,productId);
        return ResponseEntity.status(200).body("Done");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer productId){
       productService.deleteProduct(productId);
        return ResponseEntity.status(200).body("Done");

    }

    @GetMapping("/product-id/{id}")
    public ResponseEntity getProductById(@AuthenticationPrincipal @PathVariable Integer id){
        Product p =productService.getProductById(id);
        return ResponseEntity.status(200).body(p);
    }
}
