package com.parcial.procesos.services;

import com.parcial.procesos.models.Product;
import com.parcial.procesos.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }

    public Product updateProduct (Long id, Product product){
        Product productDB = productRepository.findById(id).get();
        productDB.setTitle(product.getTitle());
        productDB.setPrice(product.getPrice());
        productDB.setCategory(product.getCategory());
        productDB.setDescription(product.getDescription());
        productDB.setImage(product.getImage());
        productDB.setRating(product.getRating());

        return productRepository.save(productDB);
    }

}
