package com.example.ex2;

import com.example.ex2.Model.MyUser;
import com.example.ex2.Model.Order;
import com.example.ex2.Model.Product;
import com.example.ex2.Repository.MyUserRepository;
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
public class MyUserRepositoryTests {

    @Autowired
    MyUserRepository myUserRepository;

    MyUser myUser1;
    List<MyUser>myUsers;

    @BeforeEach
    void SetUp() {
        myUser1 = new MyUser(null, "Reem", "12345", "Admin", null);
    }
    @Test
    public void findMyUserByUsername(){
        myUserRepository.save(myUser1);
        MyUser myUser=myUserRepository.findMyUserByUsername(myUser1.getUsername());
        Assertions.assertThat(myUsers).isEqualTo(myUser);
    }
  @Test
  public void findUserById(){
   myUserRepository.save(myUser1);
   MyUser myUser=myUserRepository.findMyUserById(myUser1.getId());
   Assertions.assertThat(myUsers).isEqualTo(myUser);
  }


}
