package com.subhankar.blogappbackend.services;

import com.subhankar.blogappbackend.dto.PostDto;
import com.subhankar.blogappbackend.entities.Category;
import com.subhankar.blogappbackend.entities.Post;
import com.subhankar.blogappbackend.entities.User;
import com.subhankar.blogappbackend.exceptions.ResourceNotFoundException;
import com.subhankar.blogappbackend.payloads.GetAllResponse;
import com.subhankar.blogappbackend.repositories.CategoryRepository;
import com.subhankar.blogappbackend.repositories.PostRepository;
import com.subhankar.blogappbackend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto,Integer categoryId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        Post post =modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category);
        post.setImageName("default.png");
        post.setCreatedDate(new Date());
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post =postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCreatedDate(new Date());

        return modelMapper.map(postRepository.save(post),PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer id) {
        return modelMapper.map(postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id",id)),PostDto.class);
    }

    @Override
    public GetAllResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if(sortDir.equals("desc")){
            sort=Sort.by(Sort.Direction.DESC,sortBy);
        }else{
            sort=Sort.by(Sort.Direction.ASC,sortBy);
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDto> postDtos = postPage.getContent().stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setContent(postDtos);
        getAllResponse.setPageNumber(postPage.getNumber());
        getAllResponse.setPageSize(postPage.getSize());
        getAllResponse.setTotalElements(postPage.getNumberOfElements());
        getAllResponse.setTotalPages(postPage.getTotalPages());
        getAllResponse.setLast(postPage.isLast());

        return getAllResponse;

    }

    @Override
    public List<PostDto> getPostByCategory(Integer id) {
        return  postRepository.findAllByCategory(categoryRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Category","id",id)))
                .stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<PostDto> getPostByUser(Integer id) {
        return  postRepository.findAllByUser(userRepository.findById(id).orElseThrow(()->
                        new ResourceNotFoundException("User","id",id)))
                .stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public GetAllResponse searchPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir,String keyword) {
        Sort sort = null;
        if(sortDir.equals("desc")){
            sort=Sort.by(Sort.Direction.DESC,sortBy);
        }else{
            sort=Sort.by(Sort.Direction.ASC,sortBy);
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage = postRepository.findByTitleContaining(keyword,pageable);
        List<PostDto> postDtos = postPage.getContent().stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setContent(postDtos);
        getAllResponse.setPageNumber(postPage.getNumber());
        getAllResponse.setPageSize(postPage.getSize());
        getAllResponse.setTotalElements(postPage.getNumberOfElements());
        getAllResponse.setTotalPages(postPage.getTotalPages());
        getAllResponse.setLast(postPage.isLast());

        return getAllResponse;
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id",id)));

    }
}
