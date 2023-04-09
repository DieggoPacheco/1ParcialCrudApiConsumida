package com.parcial.procesos.controllers;

import com.parcial.procesos.models.Product;
import com.parcial.procesos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public List<Product> createAllProducts() {
        RestTemplate restTemplate = new RestTemplate();
        Product[] productosExternos = restTemplate.getForObject("https://fakestoreapi.com/products", Product[].class);
        List<Product> productosLocales = new ArrayList<>();
        for (Product productoExterno : productosExternos) {
            Product productoLocal = new Product();
            productoLocal.setTitle(productoExterno.getTitle());
            productoLocal.setCategory(productoExterno.getCategory());
            productoLocal.setPrice(productoExterno.getPrice());
            productoLocal.setDescription(productoExterno.getDescription());
            productoLocal.setImage(productoExterno.getImage());
            productosLocales.add(productService.createProduct(productoLocal));
        }
        return productosLocales;
    }

    @PostMapping(value = "/product")
    public ResponseEntity createProduct(@RequestBody Product product){
        Map response = new HashMap();
        try{
            response.put("message","Producto creado correctamente");
            response.put("data",productService.createProduct(product));
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.put("message","Error al crear producto");
            response.put("data",e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

}