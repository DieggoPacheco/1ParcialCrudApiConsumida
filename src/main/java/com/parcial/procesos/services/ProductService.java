package com.parcial.procesos.services;

import com.parcial.procesos.models.Product;

import java.util.List;

public interface ProductService{

    List<Product> createAllProducts();

    Product createProduct(Product product);

    List<Product> getAll();

    Product getProductById (Long id);

    Product updateProduct(Long id,Product product);


}
