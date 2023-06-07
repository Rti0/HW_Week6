package com.example.auth.Controller;

import com.example.auth.Model.MyUser;
import com.example.auth.Model.ToDo;
import com.example.auth.Service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @GetMapping("/get")
    public ResponseEntity getToDo(@AuthenticationPrincipal MyUser myUser) {
        List<ToDo> toDoList = toDoService.getTodos(myUser.getId());
        return ResponseEntity.status(200).body(toDoList);
    }

    @PostMapping("/add")
    public ResponseEntity addTodos(@AuthenticationPrincipal MyUser myUser, @RequestBody ToDo toDo){
        toDoService.addTodo(myUser.getId(),toDo);
        return ResponseEntity.status(200).body("Todo Added");
    }
    

    @PutMapping("update/{todoId}")
    public ResponseEntity updateToDo(@AuthenticationPrincipal MyUser myUser, @RequestBody ToDo toDo, @PathVariable Integer todoId) {
        toDoService.updateTodo(myUser.getId(), toDo, todoId);
        return ResponseEntity.status(200).body("Todo updated");
    }

    @DeleteMapping("delete/{todoId}")
    public ResponseEntity deleteToDo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer todoId) {
        toDoService.deleteToDo(myUser.getId(), todoId);
        return ResponseEntity.status(200).body("Todo Deleted");

    }
}
