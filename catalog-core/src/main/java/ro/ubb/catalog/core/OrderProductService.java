package ro.ubb.catalog.core;

import ro.ubb.catalog.core.model.Orders;
import ro.ubb.catalog.core.model.OrderProduct;
import ro.ubb.catalog.core.repository.OrderProductSet;

import java.util.concurrent.Future;

public interface OrderProductService {
    public Future<String> addProduct(OrderProduct orderProduct);
    public Future<String> removeProduct(Integer orderProductId);
    public Future<String> updateProduct(OrderProduct orderProduct);
    public Future<ProductList> getAllProducts();
    public Future<String> deleteContainingOrder(Integer id);
    public Future<OrdersList> getOrders();
    public Future<String> deleteOrder(Integer orderId);
    public Future<String> addOrder(Orders order);
    public Future<OrderProductSet> getProductsForOrder(Integer orderId);

    String ADD_PRODUCT = "add_product";
    String REMOVE_PRODUCT = "remove_product";
    String UPDATE_PRODUCT = "update_product";
    String GET_ALL_PRODUCTS = "get_all_products";
    String DELETE_FROM_ORDER = "delete_from_order";
    String GET_ORDERS = "get_orders";
    String DELETE_ORDER = "delete_eorder";
    String GET_ORDER_PRODUCTS = "get_order_products";
    String ADD_ORDER = "add_order";
}
