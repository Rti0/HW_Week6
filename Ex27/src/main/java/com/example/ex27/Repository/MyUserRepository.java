package com.example.ex27.Repository;

import com.example.ex27.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
MyUser findUsersByUsername(String username);

    MyUser getUsersById(Integer id);

   MyUser findUserById(Integer userId);
}
