package com.example.ex2.Model;

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
@Table(name = "myOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "int not null ")
    private Integer quantity;

    @Column(columnDefinition = "int not null ")
    private Integer totalPrice;

    @Column(columnDefinition = "varchar(20) not null ")
    private String dateReceived;


    @Column(columnDefinition = "varchar(20) not null check( status='new' or status='inProgress' or status='completed' )")
    private String status;


    private Integer userId;



    @ManyToOne
    @JoinColumn(name = "myUser_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;



    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    private Product products;
}
