package com.example.ex2.Repository;

import com.example.ex2.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findUserByUsername(String username);

    MyUser getUsersById(Integer id);

    MyUser findUserById(Integer userId);


    MyUser findCustomerById(Integer id);
}
