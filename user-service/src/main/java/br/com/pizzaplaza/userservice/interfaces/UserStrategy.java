package br.com.pizzaplaza.userservice.interfaces;

import br.com.pizzaplaza.entity.dto.UserDto;

import java.util.List;

public interface UserStrategy {
    UserDto save(UserDto userDto);
    boolean supports(String userType);
    List<UserDto> findAll();
}
