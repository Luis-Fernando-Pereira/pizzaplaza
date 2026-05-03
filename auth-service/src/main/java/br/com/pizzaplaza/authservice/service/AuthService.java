package br.com.pizzaplaza.authservice.service;

import br.com.pizzaplaza.authservice.repository.UserRepository;
import br.com.pizzaplaza.entity.dtos.LoginDto;
import br.com.pizzaplaza.entity.actors.User;
import br.com.pizzaplaza.util.PasswordUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    @Inject
    JsonWebToken jwt;

    public String generateJwt(User user) {
        Set<String> roles = new HashSet<>(
                Arrays.asList("admin", "seller"));

        long duration = System.currentTimeMillis() + 3600;

        return Jwt.issuer("auth-service")
                .subject(user.getOid())
                .groups(roles)
                .expiresAt(duration)
                .sign();
    }

    @Transactional
    public User authenticate(LoginDto loginData) {

        User user = userRepository.findByEmail(loginData.email);

        if (user == null) {
            return null;
        }

        if (!PasswordUtil.isPasswordValid(loginData.password, user.getPassword())) {
            return null;
        }

        user.setAuthenticated(true);

        user = userRepository.update(user);

        return user;
    }

    @Transactional
    public User authenticate() {

        String email = jwt.getIssuer();

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        user.setAuthenticated(true);

        user = userRepository.update(user);

        return user;
    }
}
