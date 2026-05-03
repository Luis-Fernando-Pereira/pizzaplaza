package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.entity.enums.UserType;
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

        User user = new User();

        user.setEmail(userDto.email);
        user.setPassword(PasswordUtil.hash(userDto.password));
        user.setAuthenticated(false);
        user.setName(userDto.getName());
        user.setCpf(userDto.getCpf());

        userRepository.save(user);

        Admin admin = new Admin();

        admin.setUser(user);

        adminRepository.save(admin);

        userDto.setOid(user.getOid());

        return userDto;
    }

    @Override
    public void delete(String oid) {
        Admin admin = adminRepository.findByOid(oid)
                .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado: " + oid));

        adminRepository.delete(admin);
    }

    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }

    @Override
    public List<UserDto> findAll() {

        List<Admin> results = adminRepository.findAll();

        return results.stream()
                .map(this::convertAdminToUserDto)
                .toList();
    }

    @Override
    public UserDto findByOid(String oid) {
        return adminRepository.findByOid(oid)
                .map(this::convertAdminToUserDto)
                .orElse(null);
    }

    private UserDto convertAdminToUserDto(Admin admin) {
        User user = admin.getUser();
        UserDto dto = new UserDto();

        dto.setOid(user.getOid());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setUserType(UserDto.Type.ADMIN);

        return dto;
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
