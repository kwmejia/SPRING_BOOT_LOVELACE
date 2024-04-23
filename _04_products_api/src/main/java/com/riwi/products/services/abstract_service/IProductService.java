package com.riwi.products.services.abstract_service;

import java.util.List;

import com.riwi.products.entities.Product;

/**
 * Utilizamos una interfaz para ser utilizada como inyección
 * de dependencias en controlador, matiene integridad, desacoplamiento
 * y principios SOLID
 */
public interface IProductService {
    public Product save(Product objProduct);

    public List<Product> getAll();

    public Product getById(Long id);

    public boolean delete(Long id);

    public Product update(Product objProduct);

    /* SpringbootApp.bind(IProductService,ProductService) */
}
