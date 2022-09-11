package com.subhankar.blogappbackend.controllers;

import com.subhankar.blogappbackend.dto.PostDto;
import com.subhankar.blogappbackend.dto.UserDto;
import com.subhankar.blogappbackend.entities.Post;
import com.subhankar.blogappbackend.payloads.ApiResponse;
import com.subhankar.blogappbackend.payloads.GetAllResponse;
import com.subhankar.blogappbackend.services.FileService;
import com.subhankar.blogappbackend.services.PostService;
import com.subhankar.blogappbackend.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/users/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        return new ResponseEntity<>(postService.createPost(postDto,categoryId,userId), HttpStatus.CREATED);

    }

    @GetMapping("/category/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(postService.getPostByCategory(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(postService.getPostByUser(id),HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<GetAllResponse> getAllPosts(@RequestParam(name = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = "5",required = false) Integer pageSize,
                                                      @RequestParam(name = "sortBy",defaultValue = "id", required = false) String sortBy,
                                                      @RequestParam(name = "sortDir", defaultValue = "asc",required = false) String sortDir){
        return new ResponseEntity<>(postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") Integer id){
        return new ResponseEntity<>(postService.updatePost(postDto,id), HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<GetAllResponse> searchPosts(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                      @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                      @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir,
                                                      @PathVariable(name = "keyword") String keyword){
        return new ResponseEntity<>(postService.searchPost(pageNumber,pageSize,sortBy,sortDir, keyword),HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Integer id){
        postService.deletePost(id);
        return new  ResponseEntity(new ApiResponse("Post deleted Successfully",true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    //Post Image Upload
    @PostMapping("/post/{id}/image/upload")
    public ResponseEntity<PostDto> uploadImage(@PathVariable(name = "id")Integer id,
                                                     @RequestParam("image")MultipartFile image) throws IOException {
        PostDto postDto = postService.getPostById(id);
        String fileName = fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);

    }

    @GetMapping(value = "/post/image/{imageName}/download",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName,
                              HttpServletResponse response) throws IOException{
        InputStream resource =fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

    @GetMapping(value = "/post/{id}/image/download",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImageByPostId(@PathVariable Integer id,
                              HttpServletResponse response) throws IOException{
        String imageName = postService.getPostById(id).getImageName();
        InputStream resource =fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
