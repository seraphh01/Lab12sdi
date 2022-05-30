package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.OrderProduct;
import ro.ubb.catalog.core.model.OrderTotal;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.core.repository.OrderProductRepository;
import ro.ubb.catalog.core.repository.OrderProductSet;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderProductService implements IOrderProductService{
    @Autowired
    private OrderProductRepository repository;

    public void addProduct(OrderProduct orderProduct)  {
        //repository.save(orderProduct);
        var op = repository.findFirstByProductIDAndOrderID(orderProduct.getProductID(), orderProduct.getOrderID());
        if(op != null) {
            op.setQuantity(op.getQuantity() + orderProduct.getQuantity());

            repository.save(op);
        }else{
            repository.save(orderProduct);
        }
    }

    /**
     * Remove an order product from the repository
     * @param orderPaymentId ID of the order payment to remove
     */
    public void removeProduct(Long orderPaymentId) {
        repository.deleteById(orderPaymentId);
    }

    /**
     * Update an order product from the repository
     * @param orderProduct updated version of the order product
     */
    public void updateProduct(OrderProduct orderProduct) {
        var op = repository.findById(orderProduct.getId()).orElseThrow();
        op.setQuantity(orderProduct.getQuantity());

        repository.save(op);
    }

    /**
     * @return Set containing all the order products from the repository
     */
    public OrderProductSet getAllProducts() {
        var set = new OrderProductSet();
        Iterable<OrderProduct> Products = repository.findAll();

        set.set = StreamSupport.stream(Products.spliterator(), false).collect(Collectors.toSet());
        System.out.println(set.set.size());
        return set;
    }

    public void deleteContainingOrder(Integer id) {
        var products = repository.findAll();
        StreamSupport.stream(products.spliterator(), false)
                .filter(p -> Objects.equals(p.getOrderID(), id))
                .forEach(product -> repository.deleteById(product.getId()));
    }

    @Override
    public OrderProductSet getProductsFromOrder(Integer orderID) {
        var list = repository.findAll().stream().filter(orderProduct -> Objects.equals(orderProduct.getOrderID(), orderID)).collect(Collectors.toSet());
        var set = new OrderProductSet();
        set.set = list;

        return set;
    }

    @Override
    public List<OrderTotal> getOrderTotals() {
        return this.repository.orderTotals();
    }


    @Override
    public List<String> getProductsReport() {
        return repository.getProductsReport();
    }
}
