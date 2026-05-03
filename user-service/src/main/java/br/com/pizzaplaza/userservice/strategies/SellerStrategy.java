package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.entity.systemactor.Admin;
import br.com.pizzaplaza.entity.systemactor.Client;
import br.com.pizzaplaza.entity.systemactor.Seller;
import br.com.pizzaplaza.entity.systemactor.User;
import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.userservice.repository.UserRepository;
import jakarta.inject.Inject;

import java.util.List;

public class SellerStrategy implements UserStrategy {
    @Inject
    UserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public boolean supports(String userType) {
        return false;
    }

    @Override
    public List<UserDto> findAll() {

        List<Seller> results = userRepository.findAllUserSeller();

        return results.stream()
                .map(this::convertSellerToUserDto)
                .toList();
    }

    @Override
    public UserDto findByOid(String oid) {
        return userRepository.findSellerByOid(oid)
                .map(this::convertSellerToUserDto)
                .orElse(null);
    }

    private UserDto convertSellerToUserDto(Seller seller) {
        User user = seller.getUser();
        UserDto dto = new UserDto();

        dto.setOid(user.getOid());
        dto.setEmail(user.getEmail());
        dto.setName(seller.getName());
        dto.setUserType(UserDto.Type.ADMIN);

        return dto;
    }
}
