package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Product;

import java.util.List;
import java.util.Set;

public interface IProductService {

    Product addProduct(Product Product);

    void deleteProduct(Product product);

    void deleteProduct(long productId);

    Product updateProduct(Product product);

    List<Product> getAllProducts();

    List<Product> findAllByName(String name);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> getProductsOrderedByPriceDesc();
}
