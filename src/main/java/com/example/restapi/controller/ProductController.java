package com.example.restapi.controller;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import com.example.restapi.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="/api/products/")
public class ProductController {

    private ProductRepository productRepository;
    private ProductsService productsService;
    private Logger LOG= LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void productRepository(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @RequestMapping(path= "{id}" , method= RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") String id){
        //return productRepository.findById(id).orElse(null);
        return productsService.getProduct(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave){

        //return productRepository.save(productToSave);
        return productsService.saveProduct(productToSave);
    }

    @RequestMapping(path="{id}",method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name="id") String id){
       /* Product foundProduct =productRepository.findById(id).orElse(null);;
        //if(foundProduct!=null){
            foundProduct.setName(productToUpdate.getName());
            foundProduct.setDescription(productToUpdate.getDescription());
            foundProduct.setType(productToUpdate.getType());
            foundProduct.setCategory(productToUpdate.getCategory());
            return productRepository.save(foundProduct);
//        }else{
//            LOG.info("No products found with given id");
//            return productToUpdate;
//        }*/

        return productsService.updateProduct(productToUpdate,id);

    }

    @RequestMapping(path="{id}", method=RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name="id") String id){
        Product foundProduct=productRepository.findById(id).orElse(null);

         if(foundProduct!=null){
             productRepository.delete(foundProduct);

         }

    }



     


}
