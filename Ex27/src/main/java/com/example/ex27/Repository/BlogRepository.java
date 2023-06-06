package com.example.ex27.Repository;

import com.example.ex27.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
   List<Blog> findBlogByUserId(Integer userId);
   Blog findBlogById(Integer id);
   Blog findBlogByTitle(String title);

}
