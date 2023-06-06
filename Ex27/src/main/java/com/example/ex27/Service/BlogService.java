package com.example.ex27.Service;

import com.example.ex27.ApiException.ApiException;
import com.example.ex27.Model.Blog;
import com.example.ex27.Model.MyUser;
import com.example.ex27.Repository.BlogRepository;
import com.example.ex27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final MyUserRepository myUserRepository;

    public List<Blog> getBlogs(Integer blogId){
        return blogRepository.findBlogByUserId(blogId);
    }

    public void addBlog(Integer userId, Blog blog) {
        MyUser myUser =myUserRepository.findUserById(userId);
        if (myUser==null)
            throw new RuntimeException("id not found");
       blog.setMyUser(myUser);
       blogRepository.save(blog);
    }
    public void updateBlog(Integer userId, Blog blog,Integer blogId) {
        Blog blog1 = blogRepository.findBlogById(blogId);
        MyUser myUser =myUserRepository.findUserById(userId);
        if (blog1 == null || myUser==null) {
            throw new ApiException("not found");
        }
        if (blog1.getUserId() != userId){
            throw new ApiException("Error");
        }
        blog1.setBody(blog.getBody());
        blog1.setTitle(blog.getTitle());
        blogRepository.save(blog1);
    }
    public void deleteBlog(Integer userId, Integer blogId){
        Blog blog=blogRepository.findBlogById(blogId);
        if (blog==null){
            throw new ApiException("not found");
        }
        if (blog.getUserId()!=userId){
            throw new ApiException("Error");
        }
        blogRepository.delete(blog);
    }

    public Blog getBlogByTitle(String title){
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog==null)
            throw new RuntimeException("blog not found");
        return blog;
    }
}
