package com.example.ex27.Controller;

import com.example.ex27.Model.MyUser;
import com.example.ex27.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
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
   public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        myUserService.deleteUser(myUser.getId(),id);
       return ResponseEntity.status(200).body("user deleted");
   }
    @GetMapping("/get-allBlogs")
    public ResponseEntity getBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(myUserService.getBlogs(myUser.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body("User register");
    }
    @PostMapping("/user")
    public ResponseEntity User(){
        return ResponseEntity.status(200).body("Welcome User");
    }


    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Log In");
    }


    @PostMapping("/logout")
    public ResponseEntity Logout(){
        return ResponseEntity.status(200).body("Log Out");
    }

}
