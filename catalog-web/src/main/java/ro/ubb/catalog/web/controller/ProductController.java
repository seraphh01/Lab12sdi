package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.ProductList;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.core.service.IProductService;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    Product addProduct(@RequestBody Product product){
        product = productService.addProduct(product);

        return product;
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProduct(@PathVariable long productId){
        productService.deleteProduct(productId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    ProductList getAllProducts(){
        var list = new ProductList();
        list.list =  productService.getAllProducts();
        return list;
    }

    @RequestMapping(value = "/products/filterByName/{filterName}", method =RequestMethod.GET)
    ProductList filterByName(@PathVariable String filterName){
        var list = new ProductList();
        list.list = productService.findAllByName(filterName);
        return list;
    }

    @RequestMapping(value = "/products/filterByPrice/{minimumPrice}", method =RequestMethod.GET)
    ProductList filterByPrice(@PathVariable double minimumPrice){
        var list = new ProductList();
        list.list = productService.findByPriceGreaterThan(minimumPrice);
        return list;
    }

    @RequestMapping(value = "/sortProducts", method =RequestMethod.GET)
    ProductList sortByPrice(){
        var list = new ProductList();
        list.list = productService.getProductsOrderedByPriceDesc();
        return list;
    }
}
