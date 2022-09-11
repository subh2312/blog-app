package com.subhankar.blogappbackend.controllers;

import com.subhankar.blogappbackend.dto.CommentDto;
import com.subhankar.blogappbackend.dto.PostDto;
import com.subhankar.blogappbackend.payloads.ApiResponse;
import com.subhankar.blogappbackend.payloads.GetAllResponse;
import com.subhankar.blogappbackend.services.CommentService;
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
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/users/{userId}/post/{postId}/comment")
    public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer postId){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId,userId), HttpStatus.CREATED);

    }

    @GetMapping("/post/{id}/comments")
    public ResponseEntity<Set<CommentDto>> getCommentByPost(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(commentService.getCommentByPostId(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}/comments")
    public ResponseEntity<Set<CommentDto>> getPostByUser(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(commentService.getCommentByUserId(id),HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(commentService.getCommentById(id),HttpStatus.OK);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("id") Integer id){
        return new ResponseEntity<>(commentService.updateComment(commentDto,id), HttpStatus.OK);
    }

//    @GetMapping("/posts/search/{keyword}")
//    public ResponseEntity<GetAllResponse> searchPosts(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
//                                                      @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
//                                                      @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
//                                                      @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir,
//                                                      @PathVariable(name = "keyword") String keyword){
//        return new ResponseEntity<>(commentService..searchPost(pageNumber,pageSize,sortBy,sortDir, keyword),HttpStatus.OK);
//    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Integer id){
       commentService.deleteComment(id);
        return new  ResponseEntity(new ApiResponse("Comment deleted Successfully",true,HttpStatus.OK.value()),HttpStatus.OK);
    }


}
