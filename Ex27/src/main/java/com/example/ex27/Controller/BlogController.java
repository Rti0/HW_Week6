package com.example.ex27.Controller;

import com.example.ex27.Model.Blog;
import com.example.ex27.Model.MyUser;
import com.example.ex27.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/get")
    public ResponseEntity getBlogs(@AuthenticationPrincipal MyUser myUser){
        List<Blog>blogs=blogService.getBlogs(myUser.getId());
        return ResponseEntity.status(200).body(blogs);
    }
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody Blog blog){
        blogService.addBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body("blog added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser,  @Valid @RequestBody Blog blog,@PathVariable Integer blogId){
        blogService.updateBlog(myUser.getId(),blog,blogId);
        return ResponseEntity.status(200).body("blog updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer blogId){
        blogService.deleteBlog(myUser.getId(), blogId);
        return ResponseEntity.status(200).body("blog deleted");
    }
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
      Blog blog=  blogService.getBlogByTitle(title);
        return ResponseEntity.status(200).body(blog);
    }
}