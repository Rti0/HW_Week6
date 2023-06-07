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
ToDo toDo1=toDoRepository.getToDoById(todoId);
    if (toDo1==null){
    throw new ApiException("not found");
    }
        if (toDo1.getId() != userId) {
            throw new ApiException("Error");
        }
   toDo1.setMessage(toDo.getMessage());
    toDoRepository.save(toDo1);
    }

    public void deleteToDo(Integer userId, Integer todoId) {
        ToDo toDo1=toDoRepository.getToDoById(todoId);
        if (toDo1==null){
            throw new ApiException("not found");
        }
        if (toDo1.getId() != userId) {
            throw new ApiException("Error");
        }
      toDoRepository.delete(toDo1);
    }
}
