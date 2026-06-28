package br.com.pizzaplaza.authservice.service;

import br.com.pizzaplaza.authservice.entities.User;
import br.com.pizzaplaza.authservice.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public User findByOid(String oid) {
        return userRepository.findByOid(oid)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @Transactional
    public User setAuthenticated(User user, boolean value) {
        user.setAuthenticated(value);
        return userRepository.update(user);
    }

    @Transactional
    public User register(User user) {
        if (userRepository.isEmailInUse(user.getEmail())) {
            throw new IllegalStateException("Email já está em uso");
        }
        return userRepository.save(user);
    }
}
