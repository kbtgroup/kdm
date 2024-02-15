package net.kbtgroup.kdm.repository;

import net.kbtgroup.kdm.model.Post;
import net.kbtgroup.kdm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}