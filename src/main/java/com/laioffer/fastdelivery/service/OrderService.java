package com.laioffer.fastdelivery.service;

import com.laioffer.fastdelivery.exception.OrderNotExistException;
import com.laioffer.fastdelivery.model.Orders;
import com.laioffer.fastdelivery.model.User;
import com.laioffer.fastdelivery.repository.OrderRepository;
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

    public List<Orders> listByUser(String username) {
        return orderRepository.findByUser(new User.Builder().setUsername(username).build());
    }

    public Orders findByIdAndUser(Long orderId, String username) throws OrderNotExistException {
        User user = new User.Builder().setUsername(username).build();
        Orders order = orderRepository.findByIdAndUser(orderId, user);
        if (order == null) {
            throw new OrderNotExistException("Order doesn't exist");
        }

        return order;
    }

    @Transactional
    public void add(Orders order) {
        orderRepository.save(order);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long orderId, String username) throws OrderNotExistException {
        Orders order = orderRepository.findByIdAndUser(orderId, new User.Builder().setUsername(username).build());
        if (order == null) {
            throw new OrderNotExistException("order doesn't exist");
        }

        orderRepository.deleteById(orderId);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void update(Long id, String status) {
        Orders order = orderRepository.getById(id);
        order.setStatus(status);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Orders> getAllOrders(){
        return orderRepository.findAll();

    }
}
