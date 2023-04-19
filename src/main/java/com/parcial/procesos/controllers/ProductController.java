package com.parcial.procesos.controllers;

import com.parcial.procesos.models.Product;
import com.parcial.procesos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity createAllProducts() {
        Map response = new HashMap();
        try{
            response.put("message","Producto consimidos correctamente en la BD");
            response.put("data",productService.createAllProducts());
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.put("message","Error al consumir los producto");
            response.put("data",e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/products")
    public ResponseEntity getAll() {
        Map response = new HashMap();
        try {
            List<Product> productList = productService.getAll();
            if (productList.size() > 0) {
                response.put("message", "se encontraron los productos");
                response.put("data", productService.getAll());
                return new ResponseEntity(response, HttpStatus.OK);
            } else {
                response.put("message", "no se encontraron los productos");
                response.put("data", null);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("message", "no se encontraron los productos");
            response.put("data", null);//userService.getUserById(id) //Optional.Empty //"e.getMessage()"
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping(value = "/products/{id}")
    public ResponseEntity getProduc(@PathVariable(name = "id")Long id){
        Map response = new HashMap();
        try{
            response.put("mensaje", "se encontro el producto");
            response.put("data", productService.getProductById(id));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("mensaje","error al buscar el producto");
            response.put("data",e.getMessage());
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product){
        Map response = new HashMap();
        try{
            response.put("message", "producto actualizado correctamente");
            response.put("data", productService.updateProduct(id, product));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("message","El producto no se encontro");
            response.put("data",id);
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }

}