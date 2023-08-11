package com.laioffer.fastdelivery.repository;

import com.laioffer.fastdelivery.model.Authority;
import com.laioffer.fastdelivery.model.Order;
import com.laioffer.fastdelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    Order findByIdAndUser(Long id, User user);
}
