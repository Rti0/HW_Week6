package com.example.ex27.Service;

import com.example.ex27.ApiException.ApiException;
import com.example.ex27.Model.Blog;
import com.example.ex27.Model.MyUser;
import com.example.ex27.Repository.BlogRepository;
import com.example.ex27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
private final MyUserRepository myUserRepository;
private final BlogRepository blogRepository;
    public MyUser getUser(Integer userId){
        return myUserRepository.getUsersById(userId);
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
    public List<Blog> getBlogs(Integer userId){
        MyUser myUser=myUserRepository.getUsersById(userId);
        if (myUser==null)
            throw new RuntimeException("id not found");
        return blogRepository.findBlogByUserId(myUser.getId());
    }
    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
       myUser.setPassword("USER");
        myUserRepository.save(myUser);
    }
}
