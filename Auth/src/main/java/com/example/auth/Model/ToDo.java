package com.example.auth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.net.ssl.SSLSession;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String message;


//    private Integer userId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="userId",referencedColumnName = "id" )
    private MyUser myUser;


}

