package com.example.EcommerceJWT.Services;

import com.example.EcommerceJWT.Model.Product;
import com.example.EcommerceJWT.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deletebyid(Integer userId) {
        productRepository.deleteById(userId);
    }

    public List<Product> getallproducts() {
        return productRepository.findAll();
    }


    public Optional<Product> updateProduct(Product product){
        Optional<Product> updateProduct = Optional.of(productRepository.saveAndFlush(product));
        int id = product.getId();
        if(productRepository.existsById(id)) return updateProduct;
        return null;
    }

    public Optional<Product> findproductbyid(int id){
        return productRepository.findById(id);
    }
    public Product updateProduct(Product product,int id){
        Product product1= findproductbyid(id).get();
        if(product.getName()!=null)product1.setName(product.getName());
        if(product.getPrice()>0)product1.setPrice(product.getPrice());
        if(product.getDescription()!=null)product1.setDescription(product.getDescription());
        return productRepository.save(product1);
    }

    public List<Product> getProductByField(Product product){
        String name=product.getName();
        List<Product> productList=productRepository.findByName(name);
        return productList;
    }

}
