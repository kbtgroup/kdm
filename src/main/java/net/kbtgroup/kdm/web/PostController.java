package net.kbtgroup.kdm.web;

import net.kbtgroup.kdm.model.Post;
import net.kbtgroup.kdm.model.User;
import net.kbtgroup.kdm.service.PostService;
import net.kbtgroup.kdm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts") // Modificato per usare /posts invece di /
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String postsHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("posts", postService.findPostsByUser(user));
        return "posts"; // Assicurati di avere un template Thymeleaf chiamato 'posts.html'
    }

    @PostMapping("/add")
    public String addPost(Post post, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        postService.savePostForUser(post, user);
        return "redirect:/posts"; // Reindirizza nuovamente alla pagina dei post
    }
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/"; // Assicurati che questo reindirizzamento sia corretto per la tua logica di applicazione
    }
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @RequestParam("content") String content) {
        postService.updatePost(id, content);
        return "redirect:/"; // Reindirizza nuovamente alla pagina dei post
    }
}