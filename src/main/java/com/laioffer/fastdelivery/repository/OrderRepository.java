package com.laioffer.fastdelivery.repository;

import com.laioffer.fastdelivery.model.Authority;
import com.laioffer.fastdelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
