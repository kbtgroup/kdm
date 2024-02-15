package net.kbtgroup.kdm.service;

import net.kbtgroup.kdm.model.Post;
import net.kbtgroup.kdm.model.User;

import java.util.List;

public interface PostService {
    Post savePostForUser(Post post, User user);
    List<Post> findPostsByUser(User user);
    void deletePost(Long id);
    Post updatePost(Long id, String newContent);
}