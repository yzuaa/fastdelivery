package com.laioffer.fastdelivery.service;
import com.laioffer.fastdelivery.model.*;
import com.laioffer.fastdelivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void update(Long id, String status) {
        Orders orders = orderRepository.getById(id);
        orders.setStatus(status);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Orders> getAllOrders(){
        return orderRepository.findAll();

    }

}
