package com.riwi.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.products.entities.Product;
import com.riwi.products.services.abstract_service.IProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final IProductService objIProductService;

    @GetMapping
    /* ResponseEntity lo utilizamos para responder con los status http */
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(this.objIProductService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.objIProductService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Product> insert(
            @RequestBody Product objProduct) {
        return ResponseEntity.ok(this.objIProductService.save(objProduct));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> update(
            @RequestBody Product objProduct, /** Para obtener los datos actualizados de l producto */
            @PathVariable Long id /** Obtener el id que viene en URL */
    ) {
        objProduct.setId(id);
        return ResponseEntity.ok(this.objIProductService.update(objProduct));
    }
}
