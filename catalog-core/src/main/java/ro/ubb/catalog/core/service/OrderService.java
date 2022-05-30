package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Orders;
import ro.ubb.catalog.core.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository repository;

    public Orders addOrder(Orders Order) {
        return repository.save(Order);
    }

    @Override
    public Orders getOrder(long orderId) {
        return repository.findById(orderId).get();
    }

    public void deleteOrder(Long orderId) {
        repository.deleteById(orderId);
    }

    public Orders updateOrder(Orders order) {
        var o = repository.findById(order.getId()).orElseThrow();
        o.setDetails(order.getDetails());

        return o;
    }

    public void deleteOrder(Orders order) {
        repository.delete(order);
    }

    public List<Orders> getAllOrders() {
        Iterable<Orders> Orders = repository.findAll();
        return StreamSupport.stream(Orders.spliterator(), false).collect(Collectors.toList());
    }
}