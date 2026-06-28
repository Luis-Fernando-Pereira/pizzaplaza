package br.com.pizzaplaza.userservice.interfaces;

import br.com.pizzaplaza.userservice.dtos.UserDto;
import br.com.pizzaplaza.userservice.entities.User;
import br.com.pizzaplaza.userservice.enums.UserType;

import java.util.List;

public interface ActorStrategy {
    UserDto save(UserDto userDto, User user);
    UserDto update(UserDto userDto, User user);
    void delete(String oid);
    UserType getType();
    List<UserDto> findAll();
    UserDto findByOid(String oid);
}
