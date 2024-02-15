package net.kbtgroup.kdm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.kbtgroup.kdm.service.PostService;
import net.kbtgroup.kdm.service.UserService;
import net.kbtgroup.kdm.model.Post;
import net.kbtgroup.kdm.model.User;
@Controller
public class MainController {
	 @Autowired
	    private PostService postService;

	    @Autowired
	    private UserService userService;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	 @GetMapping("/")
	    public String home(Model model) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String email = auth.getName(); // Ottiene l'email dell'utente autenticato
	        User user = userService.findByEmail(email);
	        model.addAttribute("posts", postService.findPostsByUser(user));
	        return "index";
	    }
	 @PostMapping("/addPost")
	    public String addPost(@RequestParam("content") String content, Model model, Authentication authentication) {
	        String email = authentication.getName();
	        User user = userService.findByEmail(email);
	        Post newPost = new Post(content, user); // Assumi che tu abbia un costruttore appropriato in Post
	        postService.savePostForUser(newPost, user);
	        
	        // Aggiungi nuovamente i post al modello per l'aggiornamento
	        model.addAttribute("posts", postService.findPostsByUser(user));
	        
	        return "redirect:/"; // Reindirizza alla homepage
	    }
}
