package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.OrderProduct;
import ro.ubb.catalog.core.model.OrderTotal;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.core.repository.OrderProductSet;

import java.util.List;


public interface IOrderProductService {
    void addProduct(OrderProduct orderProduct);

    void removeProduct(Long orderPaymentId);

    void updateProduct(OrderProduct orderProduct);

    OrderProductSet getAllProducts();

    void deleteContainingOrder(Integer id);

    OrderProductSet getProductsFromOrder(Integer orderID);
    List<OrderTotal> getOrderTotals();
    List<String> getProductsReport();
}
