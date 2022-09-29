package com.example.h2deneme.controller;

import com.example.h2deneme.model.ProductIDModel;
import com.example.h2deneme.model.ProductModel;
import com.example.h2deneme.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.activation.DataHandler;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {


    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody ProductModel productModel){

        ProductModel new_product = new ProductModel(productModel.getName(), productModel.getDescription(), productModel.getPrice());
        new_product = productRepository.save(new_product);
        return new ResponseEntity(new ProductIDModel(new_product.getId()), HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProductModel>> getProducts(@RequestBody ProductModel productModel){
        return new ResponseEntity<List<ProductModel>>(productRepository.findAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductModel> getProductByID(@RequestBody ProductModel productModel, @PathVariable Long id){

        ProductModel find_product = new ProductModel();
        find_product = productRepository.findById(id).orElseThrow(()->new ProductDoesNotExist());

        if (find_product != null){
            return new ResponseEntity<ProductModel>(find_product, HttpStatus.OK);
        } else {
            return new ResponseEntity<ProductModel>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) // 409
    class ProductDoesNotExist extends RuntimeException{
        public ResponseEntity ProductDoesNotExist(){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
