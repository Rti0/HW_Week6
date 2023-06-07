package com.example.auth.Service;

import com.example.auth.ApiException.ApiException;
import com.example.auth.Model.MyUser;
import com.example.auth.Model.ToDo;
import com.example.auth.Repository.AuthRepository;
import com.example.auth.Repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final AuthRepository authRepository;
    public List<ToDo> getTodos(Integer userId) {
        return toDoRepository.findToDoByUserId(userId);
    }
    public void addTodo(Integer userId, ToDo toDo) {
        MyUser myUser= authRepository.findMyUserById(userId);
        toDo.setMyUser(myUser);
        toDoRepository.save(toDo);
    }

    public void updateTodo(Integer userId, ToDo toDo,Integer todoId) {
        ToDo old = toDoRepository.findToDoById(todoId);
        if (old == null) {

            throw new ApiException("Not found");
        }
        if (old.getMyUser().getId() != userId) {
            throw new ApiException("Error");
        }
        old.setMessage(toDo.getMessage());
        toDoRepository.save(old);
    }
    public void deleteTodo(Integer userId,Integer todoId) {
        ToDo old = toDoRepository.findToDoById(todoId);
        if (old== null) {

            throw new ApiException(" Not found");
        }
        if(old.getMyUser().getId()!=userId) {
            throw new ApiException("UAuthorized");
        }
        toDoRepository.delete(old);

    }
}
