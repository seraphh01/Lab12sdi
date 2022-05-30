package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.ubb.catalog.core.model.OrderProduct;
import ro.ubb.catalog.core.model.OrderTotal;
import ro.ubb.catalog.core.model.Product;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends org.springframework.data.jpa.repository.JpaRepository<OrderProduct, Long> {
    OrderProduct findFirstByProductIDAndOrderID(Integer productID, Integer orderID);
    @Query(value="select o.orderid, sum(total) " +
            "from " +
            "(select a.orderid, sum(a.quantity * b.price) as \"total\" " +
            "from " +
            "(select orderid, productid, quantity " +
            "from orderproduct " +
            ") a " +
            " " +
            "inner join " +
            " " +
            "(select id, price " +
            "from product) b " +
            " " +
            "on a.productid = b.id " +
            "group by a.orderid, a.productid) as o " +
            "group by o.orderid", nativeQuery = true)
    List<OrderTotal> orderTotals();

    @Query(value= "select p.name, stats.price\n" +
            "from\n" +
            "(select productId, sum(quantity) \"price\"\n" +
            "from orderproduct\n" +
            "group by productId\n" +
            ") stats\n" +
            "\n" +
            "inner join\n" +
            "product p\n" +
            "on p.id = stats.productid\n" +
            "order by stats.price desc", nativeQuery=true)
    List<String> getProductsReport();
}
