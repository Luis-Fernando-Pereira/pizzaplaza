package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import br.com.pizzaplaza.entity.dto.UserDto;

public class SellerStrategy implements UserStrategy {
    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public boolean supports(String userType) {
        return false;
    }
}
