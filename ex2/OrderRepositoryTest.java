package com.example.ex2;

import com.example.ex2.Model.MyUser;
import com.example.ex2.Model.Order;
import com.example.ex2.Model.Product;
import com.example.ex2.Repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    MyUser myUser;

    Order order1;
    Product products;
    List<Order> orderList;

    @BeforeEach
    void SetUp() {
        myUser = new MyUser(null, "Reem", "12345", "Admin", null);
        order1 = new Order(null, 1, 50, "5june", "new", null, myUser, products);
    }

    @Test
                public void findOrderById(){
            orderRepository.save(order1);
            Order order=orderRepository.findOrderById(order1.getId());
            Assertions.assertThat(order.getMyUser().getId()).isEqualTo(order);
        }

     @Test
        public void findOrderByUserId(){
            orderRepository.save(order1);
            List<Order>orders=orderRepository.findOrderByUserId(order1.getId());
            Assertions.assertThat(orders.get(0)).isEqualTo(order1);

        }

        }



