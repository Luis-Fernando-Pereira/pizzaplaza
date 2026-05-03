package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.entity.enums.UserType;
import br.com.pizzaplaza.entity.systemactor.Admin;
import br.com.pizzaplaza.entity.systemactor.Client;
import br.com.pizzaplaza.entity.systemactor.Seller;
import br.com.pizzaplaza.entity.systemactor.User;
import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.userservice.repository.SellerRepository;
import br.com.pizzaplaza.userservice.repository.UserRepository;
import br.com.pizzaplaza.util.PasswordUtil;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.Option;
import jakarta.inject.Inject;

import java.util.List;

public class SellerStrategy implements UserStrategy {

    @Inject
    UserRepository userRepository;

    @Inject
    SellerRepository sellerRepository;

    @Override
    public UserDto save(UserDto userDto) {
        User user = new User();

        user.setEmail(userDto.email);
        user.setPassword(PasswordUtil.hash(userDto.password));
        user.setAuthenticated(false);
        user.setName(userDto.getName());
        user.setCpf(userDto.getCpf());

        userRepository.save(user);

        Seller seller = new Seller();

        seller.setUser(user);

        sellerRepository.save(seller);

        userDto.setOid(user.getOid());

        return userDto;
    }

    @Override
    public void delete(String oid) {
        Seller seller = sellerRepository.findByOid(oid)
                .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado: " + oid));

        sellerRepository.delete(seller);
    }

    @Override
    public UserType getType() {
        return UserType.SELLER;
    }

    @Override
    public List<UserDto> findAll() {

        List<Seller> results = sellerRepository.findAll();

        return results.stream()
                .map(this::convertSellerToUserDto)
                .toList();
    }

    @Override
    public UserDto findByOid(String oid) {
        return sellerRepository.findByOid(oid)
                .map(this::convertSellerToUserDto)
                .orElse(null);
    }

    private UserDto convertSellerToUserDto(Seller seller) {
        User user = seller.getUser();
        UserDto dto = new UserDto();

        dto.setOid(user.getOid());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setUserType(UserDto.Type.ADMIN);

        return dto;
    }
}
