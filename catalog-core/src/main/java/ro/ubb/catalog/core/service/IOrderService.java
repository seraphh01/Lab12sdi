package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Orders;

import java.util.List;

public interface IOrderService {

    Orders addOrder(Orders Order);

    Orders getOrder(long orderId);

    void deleteOrder(Long orderId);

    Orders updateOrder(Orders order);

    void deleteOrder(Orders order);

    List<Orders> getAllOrders();
}
