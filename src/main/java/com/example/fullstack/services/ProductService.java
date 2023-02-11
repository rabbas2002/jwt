package com.example.fullstack.services;

import com.example.fullstack.model.Product;
import com.example.fullstack.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product>getAllProducts(){
        return productRepository.findAll();
    }
    public Product addProducts(Product product){
        return productRepository.save(product);
    }
}
