package com.example.ex2.Repository;

import com.example.ex2.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {


    MyUser getUsersById(Integer id);

    MyUser findCustomerById(Integer id);

    MyUser findMyUserById(Integer userId);

    MyUser findMyUserByUsername(String username);

    MyUser findUserById(Integer userId);
}
