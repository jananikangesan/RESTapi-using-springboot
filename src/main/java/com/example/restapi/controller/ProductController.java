package com.example.restapi.controller;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="/api/products/")
public class ProductController {

    private ProductRepository productRepository;
    private Logger LOG= LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public void productRepository(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @RequestMapping(path= "{id}" , method= RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave){
        return productRepository.save(productToSave);
    }

    @RequestMapping(path="{id}",method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name="id") String id){
        Product foundProduct =productRepository.findById(id).orElse(null);;
        if(foundProduct!=null){
            foundProduct.setName(productToUpdate.getName());
            foundProduct.setDescription(productToUpdate.getDescription());
            foundProduct.setType(productToUpdate.getType());
            foundProduct.setCategory(productToUpdate.getCategory());
            return productRepository.save(foundProduct);
        }else{
            LOG.info("No products found with given id");
            return productToUpdate;
        }

    }

     


}
