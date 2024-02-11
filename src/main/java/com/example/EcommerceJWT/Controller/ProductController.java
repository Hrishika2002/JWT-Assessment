package com.example.EcommerceJWT.Controller;

import com.example.EcommerceJWT.Model.Product;
import com.example.EcommerceJWT.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/product/add-product")
    @ResponseStatus(HttpStatus.OK)
    public Product save(@RequestBody Product product){
        System.out.println("Check");
        return productService.saveProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public void deletebyid(@PathVariable Integer id)  {
        productService.deletebyid(id);
    }

    @GetMapping("/products")
    public List<Product> getallproducts() {
        return productService.getallproducts();
    }

    @PutMapping("/update")
    public Optional<Product> updateProduct(@RequestBody Product product){
        Optional<Product> updateProduct = productService.updateProduct(product);
        return updateProduct;
    }

    @PostMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable int id,@RequestBody Product product){
        Product updatedproduct = productService.updateProduct(product,id);
        return updatedproduct;
    }

    @GetMapping("/getProductByField")
    public List<Product> getProductByField(@RequestBody(required = false) Product product){

        if(product==null)
            return productService.getallproducts();

        return productService.getProductByField(product);

    }

}