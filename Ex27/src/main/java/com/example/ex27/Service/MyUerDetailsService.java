package com.example.ex27.Service;

import com.example.ex27.Model.MyUser;
import com.example.ex27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUerDetailsService implements UserDetailsService {


    private final MyUserRepository myUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
    MyUser myUser=myUserRepository.findUsersByUsername(username);
    if (myUser == null) {
        throw new UsernameNotFoundException("Wrong username or password");
    }

    return myUser;
}

}

