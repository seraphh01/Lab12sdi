package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.ubb.catalog.core.model.Product;

import javax.inject.Qualifier;
import java.util.List;

public interface ProductRepository extends org.springframework.data.jpa.repository.JpaRepository<Product, Long> {
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findAllByNameContains(String name);
    @Query(value="select * from product order by price desc", nativeQuery = true)
    List<Product> getSortedProductsByPrice();
}
