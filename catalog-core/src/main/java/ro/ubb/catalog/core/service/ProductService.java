package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.core.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product Product) {
        return repository.save(Product);
    }

    public void deleteProduct(Product product) {
        repository.deleteById(product.getId());
    }

    @Override
    public void deleteProduct(long productId) {
        repository.deleteById(productId);
    }

    public Product updateProduct(Product product)  {
        var p = repository.findById(product.getId()).orElseThrow();
        p.setName(product.getName());
        p.setPrice(product.getPrice());

        return p;
    }

    /**
     * Gets all the Products from the repository
     *
     * @return a set of all the products
     */
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * Filters the Products by name
     *
     * @param s the given name
     * @return a set of all the filtered Products
     */
    public List<Product> findAllByName(String s) {
        return repository.findAllByNameContains(s);
    }

    @Override
    public List<Product> findByPriceGreaterThan(double price) {
        return repository.findByPriceGreaterThan(price);
    }

    @Override
    public List<Product> getProductsOrderedByPriceDesc() {
        return repository.getSortedProductsByPrice();
    }

}
