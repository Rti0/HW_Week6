package com.example.ex2.Service;

import com.example.ex2.ApiException.ApiException;
import com.example.ex2.Model.MyUser;
import com.example.ex2.Model.Order;
import com.example.ex2.Model.Product;
import com.example.ex2.Repository.MyUserRepository;
import com.example.ex2.Repository.OrderRepository;
import com.example.ex2.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MyUserRepository myUserRepository;
    private final ProductRepository productRepository;

    public List<Order> getAllOrder(Integer orderId) {
        return orderRepository.findOrderByUserId(orderId);
    }

    public void addOrder(Integer userId, Order order, Integer productId) {
        MyUser myUser = myUserRepository.findUserById(userId);
        Product product1 = productRepository.findProductById(productId);
        if (myUser == null || product1 == null)
            throw new RuntimeException("id not found");
        order.setTotalPrice(product1.getPrice() * order.getQuantity());
        order.setStatus("now");
        order.setProducts(product1);
        order.setMyUser(myUser);
        orderRepository.save(order);
    }

    public void updateOrder(Integer userId, Order order, Integer orderId) {
        Order order1 = orderRepository.findOrderById(orderId);
        MyUser myUser = myUserRepository.findUserById(userId);
        if (order1 == null || myUser == null) {
            throw new ApiException("not found");
        }
        if (order1.getUserId() != userId) {
            throw new ApiException("Error");
        }
        order1.setId(order.getId());
        order1.setStatus(order.getStatus());
        order1.setQuantity(order.getQuantity());
        order1.setTotalPrice(order.getTotalPrice());
        order1.setDateReceived(order.getDateReceived());
        orderRepository.save(order1);
    }


    public void deleteOrder(Integer userId, Integer orderId) {
        MyUser myUser1 = myUserRepository.findUserById(userId);
        Order order = orderRepository.findOrderById(orderId);
        if (order == null || order.getStatus().equals("inProgress") || order.getStatus().equals("completed")) {
            throw new ApiException("can't Deleted");
        }
        if (myUser1.getId() != userId) {
            throw new ApiException("Error");
        }
        orderRepository.delete(order);
    }
  //  Create endpoint that change order status(only admin can change it)
public void changeStatus(Integer userId, Integer orderId,String status){
   Order order=orderRepository.findOrderById(orderId);
   if (order==null){
       throw new ApiException("Not updated");
   }

   if (order.getMyUser().getId()!=userId) {
       throw new ApiException("UnAuthorized");
   }
   order.setStatus(status);
   orderRepository.save(order);
    }

//    Get order - customer - product by Id
    public Order getOrderById(Integer id) {
        Order order = orderRepository.findOrderById(id);
        if (order == null) {
            throw new ApiException("Not found");
        }
        return order;
    }


    ////Get all customer orders
    public List<Order> getAllCustomer() {
        List<Order> orders = orderRepository.findAll();
        return orders;

    }
}
