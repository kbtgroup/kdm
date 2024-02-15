package net.kbtgroup.kdm.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import net.kbtgroup.kdm.model.User;
import net.kbtgroup.kdm.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    User findByEmail(String email); // Aggiungi questa linea
}