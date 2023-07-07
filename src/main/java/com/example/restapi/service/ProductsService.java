package com.example.restapi.service;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String id){
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product productToUpdate, String id){
        Product foundProduct =productRepository.findById(id).orElse(null);;
        if(foundProduct!=null){
        foundProduct.setName(productToUpdate.getName());
        foundProduct.setDescription(productToUpdate.getDescription());
        foundProduct.setType(productToUpdate.getType());
        foundProduct.setCategory(productToUpdate.getCategory());
        return productRepository.save(foundProduct);
        }else{
            //LOG.info("No products found with given id");
            return productToUpdate;
        }
    }
}
