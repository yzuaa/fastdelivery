package com.laioffer.fastdelivery.repository;

import com.laioffer.fastdelivery.model.Orders;
import com.laioffer.fastdelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUser(User user);

    Orders findByIdAndUser(Long id, User user);
}
