package br.com.pizzaplaza.userservice.interfaces;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.enums.UserType;
import br.com.pizzaplaza.entity.systemactor.User;

import java.util.List;

public interface UserStrategy {
    UserDto save(UserDto userDto, User user);
    UserDto update(UserDto userDto, User user);
    void delete(String oid);
    UserType getType();
    List<UserDto> findAll();
    UserDto findByOid(String oid);
}
