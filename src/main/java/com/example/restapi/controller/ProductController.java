package com.example.restapi.controller;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/products/")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @RequestMapping(path= "{id}" , method= RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id).orElse(null);
    }
}
