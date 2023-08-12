package com.laioffer.fastdelivery.controller;

import com.laioffer.fastdelivery.model.Orders;
import com.laioffer.fastdelivery.model.User;
import com.laioffer.fastdelivery.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/postman")
    public List<Orders> listOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/orders")
    public List<Orders> listUserOrders(Principal principal) {
        return orderService.listByUser(principal.getName());
    }

    @GetMapping(value = "/orders/{orderId}")
    public Orders getOrder(@PathVariable Long orderId, Principal principal) {
        return orderService.findByIdAndUser(orderId, principal.getName());
    }

    @PostMapping("/orders")
    public void addOrder(
            @RequestParam("price") double price,
            @RequestParam("weight") double weight,
            @RequestParam("description") String description,
            @RequestParam("pickup_address") String pickupAddress,
            @RequestParam("delivery_address") String deliveryAddress,
            @RequestParam("delivery_method") String deliveryMethod,
            @RequestParam("expected_delivery_time") double expectedDeliveryTime,
            Principal principal) {

        Orders order = new Orders.Builder()
                .setCreatedDate(LocalDate.now())
                .setPrice(price)
                .setWeight(weight)
                .setDescription(description)
                .setPickupAddress(pickupAddress)
                .setDeliveryAddress(deliveryAddress)
                .setDeliveryMethod(deliveryMethod)
                .setExpectedDeliveryTime(expectedDeliveryTime)
                .setUser(new User.Builder().setUsername(principal.getName()).build())
                .build();
        orderService.add(order);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrder(@PathVariable Long orderId, Principal principal) {
        orderService.delete(orderId, principal.getName());
    }

    @PutMapping(value = "/postman")
    public void updateOrders(@RequestBody Orders orders){
        orderService.update(orders.getId(), orders.getStatus());
    }
}
