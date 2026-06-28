package br.com.pizzaplaza.authservice.service;

import br.com.pizzaplaza.authservice.dtos.LoginDto;
import br.com.pizzaplaza.authservice.dtos.UserRegistrationDto;
import br.com.pizzaplaza.authservice.entities.User;
import br.com.pizzaplaza.authservice.util.PasswordUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    private static final long TOKEN_DURATION_SECONDS = 3600;

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;

    public User authenticate(LoginDto loginData) {
        User user = userService.findByEmail(loginData.email);

        if (!PasswordUtil.isPasswordValid(loginData.password, user.getPassword())) {
            throw new NotAuthorizedException("Credenciais inválidas");
        }

        return userService.setAuthenticated(user, true);
    }

    public String generateJwt(User user) {
        String role = user.getUserType().name().toLowerCase();

        return Jwt.issuer("auth-service")
                .subject(user.getOid())
                .upn(user.getEmail())
                .claim("name", user.getName())
                .groups(Set.of(role))
                .expiresAt(Instant.now().plusSeconds(TOKEN_DURATION_SECONDS))
                .sign();
    }

    public User refresh() {
        String userOid = jwt.getSubject();

        User user = userService.findByOid(userOid);

        if (!Boolean.TRUE.equals(user.getAuthenticated())) {
            throw new NotAuthorizedException("Sessão encerrada. Faça login novamente.");
        }

        return user;
    }

    public void logout() {
        String userOid = jwt.getSubject();
        User user = userService.findByOid(userOid);
        userService.setAuthenticated(user, false);
    }

    public User register(UserRegistrationDto dto) {
        User user = new User();
        user.setName(dto.name);
        user.setCpf(dto.cpf);
        user.setEmail(dto.email);
        user.setPassword(PasswordUtil.hash(dto.password));
        user.setUserType(dto.userType);
        return userService.register(user);
    }

    public void validateSession() {
        String userOid = jwt.getSubject();
        User user = userService.findByOid(userOid);
        if (!Boolean.TRUE.equals(user.getAuthenticated())) {
            throw new NotAuthorizedException("Sessão encerrada. Faça login novamente.");
        }
    }
}
