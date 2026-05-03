package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import br.com.pizzaplaza.userservice.repository.AdminRepository;
import br.com.pizzaplaza.userservice.repository.UserRepository;
import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.systemactor.Admin;
import br.com.pizzaplaza.entity.systemactor.User;
import br.com.pizzaplaza.util.PasswordUtil;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.Option;
import jakarta.inject.Inject;

import java.util.List;

public class AdminStrategy implements UserStrategy {

    @Inject
    UserRepository userRepository;

    @Inject
    AdminRepository adminRepository;

    @Override
    public UserDto save(UserDto userDto) {
        if (!isUserDtoValid(userDto)) {
            throw new InvalidValueException(new Option(),"Usuário inválido");
        }

        User user = new User();

        user.setEmail(userDto.email);
        user.setPassword(PasswordUtil.hash(userDto.password));
        user.setAuthenticated(false);

        userRepository.save(user);

        Admin admin = new Admin();

        admin.setUser(user);
        admin.setName(userDto.getName());

        adminRepository.save(admin);

        userDto.setOid(user.getOid());

        return userDto;
    }

    @Override
    public boolean supports(String userType) {
        return "ADMIN".equals(userType);
    }

    @Override
    public List<UserDto> findAll() {

        userRepository.findByType();

        return List.of();
    }

    public Boolean isUserDtoValid(UserDto userDto) {
        if (userDto == null) {
            return false;
        }

        if (!isEmailValid(userDto.email)) {
            return false;
        }

        if (!isPasswordValid(userDto.password)) {
            return false;
        }

        return true;
    }

    public Boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty() && !password.isBlank();
    }

    public Boolean isEmailValid(String email) {
        return email != null && !email.isEmpty() && !email.isBlank();
    }
}
