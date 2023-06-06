package com.example.ex2.Controller;

import com.example.ex2.Model.MyUser;
import com.example.ex2.Model.Order;
import com.example.ex2.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getOrders(@AuthenticationPrincipal MyUser myUser){
        List<Order> orders=orderService.getAllOrder(myUser.getId());
        return ResponseEntity.status(200).body(orders);
    }
@PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser,@RequestBody Order order,@PathVariable Integer productId){
       orderService.addOrder(myUser.getId(), order, order.getTotalPrice());
       return ResponseEntity.status(200).body("order Add");
}
    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser myUser,@RequestBody Order order,@PathVariable Integer orderId){
        orderService.updateOrder(myUser.getId(), order,orderId);
        return ResponseEntity.status(200).body("order updated");
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal @PathVariable Integer userId, @PathVariable Integer orderId){
        orderService.deleteOrder(userId,orderId);
        return ResponseEntity.status(200).body("order deleted");
    }

    @PutMapping("change-statues/{orderId}/{status}")
    public ResponseEntity changeStatus(@AuthenticationPrincipal  MyUser myUser,@PathVariable Integer orderId,@PathVariable String status){
        orderService.changeStatus(myUser.getId(),orderId,status);
        return ResponseEntity.status(200).body("Status Changed");
    }
    @GetMapping("/order-id/{id}")
public ResponseEntity getOrderById(@AuthenticationPrincipal @PathVariable Integer id){
        Order order=orderService.getOrderById(id);
        return ResponseEntity.status(200).body(order);
    }
   @GetMapping("/get-customers")
    public ResponseEntity findAllCustomer(){
       List<Order>orders= orderService.getAllCustomer();
       return ResponseEntity.status(200).body(orders);
    }

}
