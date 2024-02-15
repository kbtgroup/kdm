package net.kbtgroup.kdm.service;

import net.kbtgroup.kdm.model.Post;
import net.kbtgroup.kdm.model.User;
import net.kbtgroup.kdm.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    @Override
    public Post updatePost(Long id, String newContent) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContent(newContent);
        return postRepository.save(post);
    }
    @Override
    public Post savePostForUser(Post post, User user) {
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public List<Post> findPostsByUser(User user) {
        return postRepository.findByUser(user);
    }
}