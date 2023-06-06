package com.example.ex2.Service;

import com.example.ex2.ApiException.ApiException;
import com.example.ex2.Model.MyUser;
import com.example.ex2.Repository.MyUserRepository;
import com.example.ex2.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepository myUserRepository;
    private final OrderRepository orderRepository;

    public MyUser getUser(Integer userId){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        if (myUser==null)
            throw new ApiException("user not found");
        return myUser;
    }
    public void addUser(MyUser myUser){
        myUserRepository.save(myUser);
    }

    public void updateUser(MyUser myUser, Integer userId) {
        MyUser old = myUserRepository.getUsersById(userId);
        if (old == null) {
            throw new ApiException("not found");
        }
        old.setId(myUser.getId());
        old.setUsername(myUser.getUsername());
        old.setPassword(myUser.getPassword());
        myUserRepository.save(myUser);
    }


    public void deleteUser(Integer userId, Integer id) {
        MyUser old = myUserRepository.getUsersById(userId);
        if (old == null) {
            throw new ApiException("not found");
        }
        myUserRepository.delete(old);
    }
    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setRole(hash);
        myUser.setRole("CUSTOMER");
        myUserRepository.save(myUser);
    }
    public MyUser getCustomerById(Integer id) {
       MyUser myUser = myUserRepository.findCustomerById(id);
        if (myUser == null) {
            throw new ApiException("Not found");
        }
        return myUser;
    }


}
