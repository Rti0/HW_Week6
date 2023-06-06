package com.example.ex27.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @Column(columnDefinition = "varchar(20) not null")
    private String body;


    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "myUser_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;


}
