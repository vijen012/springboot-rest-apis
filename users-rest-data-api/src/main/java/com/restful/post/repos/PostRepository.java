package com.restful.post.repos;

import org.springframework.data.repository.CrudRepository;

import com.restful.post.data.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
