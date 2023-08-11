package com.laioffer.fastdelivery.service;

import com.laioffer.fastdelivery.exception.OrderNotExistException;
import com.laioffer.fastdelivery.model.Location;
import com.laioffer.fastdelivery.model.Orders;
import com.laioffer.fastdelivery.model.User;
import com.laioffer.fastdelivery.repository.DeliveryLocationRepository;
import com.laioffer.fastdelivery.repository.OrderRepository;
import com.laioffer.fastdelivery.repository.PickupLocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final PickupLocationRepository pickupLocationRepository;

    private final DeliveryLocationRepository deliveryLocationRepository;

    private final GeoCodingService geoCodingService;

    public OrderService(OrderRepository orderRepository, PickupLocationRepository pickupLocationRepository, DeliveryLocationRepository deliveryLocationRepository, GeoCodingService geoCodingService) {
        this.orderRepository = orderRepository;
        this.pickupLocationRepository = pickupLocationRepository;
        this.deliveryLocationRepository = deliveryLocationRepository;
        this.geoCodingService = geoCodingService;
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

        Location pickupLocation = geoCodingService.getLatLng(order.getId(), order.getPickupAddress());
        pickupLocationRepository.save(pickupLocation);

        Location deliveryLocation = geoCodingService.getLatLng(order.getId(), order.getDeliveryAddress());
        deliveryLocationRepository.save(deliveryLocation);
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
