package com.example.ex2.Controller;

import com.example.ex2.Model.MyUser;
import com.example.ex2.Model.Order;
import com.example.ex2.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class MyUserController {

    private final MyUserService myUserService;


    @GetMapping("/get")
    public ResponseEntity getUser(@AuthenticationPrincipal MyUser myUser){
        myUserService.getUser(myUser.getId());
        return ResponseEntity.status(200).body(myUser);
    }

    @PostMapping("add")
    public ResponseEntity addUser(@Valid @RequestBody MyUser myUser){
        myUserService.addUser(myUser);
        return ResponseEntity.status(200).body("user Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        myUserService.updateUser(myUser,id);
        return ResponseEntity.status(200).body("user updated");

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer userId){
        myUserService.deleteUser(myUser.getId(),userId);
        return ResponseEntity.status(200).body("user deleted");
    }

    @GetMapping("/customer-id/{id}")
    public ResponseEntity getCustomerById(@AuthenticationPrincipal @PathVariable Integer userId){
        MyUser m =myUserService.getCustomerById(userId);
        return ResponseEntity.status(200).body(m);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body("User register");
    }



    @PostMapping("/admin")
    public ResponseEntity Admin(){
        return ResponseEntity.status(200).body("Welcome Admin");
    }

    @PostMapping("/customer")
    public ResponseEntity customer(){
        return ResponseEntity.status(200).body("Welcome customer");
    }


    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("LogIn");
    }


    @PostMapping("/logout")
    public ResponseEntity Logout(){
        return ResponseEntity.status(200).body("LogOut");
    }

}
