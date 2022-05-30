package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.OrderProductList;
import ro.ubb.catalog.core.OrdersList;
import ro.ubb.catalog.core.ProductList;
import ro.ubb.catalog.core.model.OrderProduct;
import ro.ubb.catalog.core.model.OrderTotal;
import ro.ubb.catalog.core.model.Orders;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.core.repository.OrderProductSet;
import ro.ubb.catalog.core.service.IOrderProductService;
import ro.ubb.catalog.core.service.IOrderService;
import ro.ubb.catalog.core.service.IProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderProductController {
    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderProductService orderProductService;

    @Autowired
    IProductService productService;

    @RequestMapping(value = "/orderProducts", method = RequestMethod.POST)
    ResponseEntity<?> addOrderProduct(@RequestBody OrderProduct product){
        try{
            orderProductService.addProduct(product);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/orderProducts/{productId}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeOrderProduct(@PathVariable Long productId){
        try{
            orderProductService.removeProduct(productId);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/orderProducts", method = RequestMethod.PUT)
    ResponseEntity<?> updateOrderProduct(@RequestBody OrderProduct orderProduct){
        try{
            orderProductService.updateProduct(orderProduct);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/orderProducts", method = RequestMethod.GET)
    OrderProductSet getAllOrders(){
        return orderProductService.getAllProducts();
    }

    @RequestMapping(value= "/orderProducts/deleteFrom/{orderId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteContaining(@PathVariable Integer orderId){
        try{
            orderProductService.deleteContainingOrder(orderId);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/orderProducts/order/{orderId}", method = RequestMethod.GET)
    OrderProductSet getProductsFromOrder(@PathVariable Integer orderId){
        try {

            return orderProductService.getProductsFromOrder(orderId);
        }catch(Exception exc){
            return new OrderProductSet();
        }
    }

    @RequestMapping(value="/orderProducts/totals", method = RequestMethod.GET)
    ResponseEntity<?> getOrderTotals()
    {
        try{
            return new ResponseEntity<>(this.orderProductService.getOrderTotals(), HttpStatus.OK);
        }catch (Exception exc){
            return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/orderProducts/report", method = RequestMethod.GET)
    ResponseEntity<?> getProductsReport(){
        var list = orderProductService.getProductsReport();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
