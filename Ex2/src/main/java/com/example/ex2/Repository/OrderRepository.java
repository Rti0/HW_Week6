package com.example.ex2.Repository;

import com.example.ex2.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Integer> {
List<Order> findOrderByUserId(Integer id);

Order findOrderById(Integer userId);


    List<Order> findAllCustomerById(Integer userId);
}
