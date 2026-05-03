package br.com.pizzaplaza.userservice.interfaces;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.enums.UserType;

import java.util.List;

public interface UserStrategy {
    UserDto save(UserDto userDto);
    UserType getType();
    List<UserDto> findAll();
    UserDto findByOid(String oid);
}
