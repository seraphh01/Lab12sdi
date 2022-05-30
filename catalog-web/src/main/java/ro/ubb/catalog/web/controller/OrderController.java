package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.OrdersList;
import ro.ubb.catalog.core.model.Orders;
import ro.ubb.catalog.core.service.IOrderService;

@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    Orders addOrder(@RequestBody Orders order){
        order = orderService.addOrder(order);

        return order;
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteOrder(@PathVariable long orderId){
        orderService.deleteOrder(orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.PUT)
    Orders updateOrder(@RequestBody Orders order){
        order = orderService.updateOrder(order);

        return order;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    OrdersList getAllOrders(){
        var list = new OrdersList();
        list.list = orderService.getAllOrders();
        return list;
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    Orders getAllOrders(@PathVariable long orderId){
        return orderService.getOrder(orderId);
    }
}
