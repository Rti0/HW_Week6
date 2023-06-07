package com.example.auth.Repository;

import com.example.auth.Model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository <ToDo,Integer> {
    List<ToDo> findToDoByUserId(Integer id);


    ToDo findToDoById(Integer todoId);


}
