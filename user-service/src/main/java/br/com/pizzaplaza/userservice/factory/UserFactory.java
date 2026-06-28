package br.com.pizzaplaza.userservice.factory;

import br.com.pizzaplaza.userservice.dtos.UserDto;
import br.com.pizzaplaza.userservice.entities.User;
import br.com.pizzaplaza.userservice.util.PasswordUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserFactory {

    public User create(UserDto userDto) {
        User user = new User();
        if (userDto.getOid() != null && !userDto.getOid().isBlank()) {
            user.setOid(userDto.getOid());
        }
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordUtil.hash(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setCpf(userDto.getCpf());
        return user;
    }
}
