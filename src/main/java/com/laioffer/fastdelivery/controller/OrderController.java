package com.laioffer.fastdelivery.controller;

import com.laioffer.fastdelivery.model.Orders;
import com.laioffer.fastdelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    public List<Orders> listOrders(){
        return orderService.getAllOrders();
    }

    @PutMapping(value = "/orders/{id}")
    public List<Orders> updateOrders(@PathVariable Long id, @RequestParam String status){
        orderService.update(id, status);
        return orderService.getAllOrders();
    }

}


















