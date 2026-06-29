package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.userservice.dtos.UserDto;
import br.com.pizzaplaza.userservice.entities.Seller;
import br.com.pizzaplaza.userservice.entities.User;
import br.com.pizzaplaza.userservice.enums.UserType;
import br.com.pizzaplaza.userservice.interfaces.ActorStrategy;
import br.com.pizzaplaza.userservice.repository.SellerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class SellerStrategy implements ActorStrategy {

    @Inject
    SellerRepository sellerRepository;

    @Override
    @Transactional
    public UserDto save(UserDto userDto, User user) {
        Seller seller = new Seller();
        seller.setUser(user);
        sellerRepository.save(seller);
        userDto.setOid(user.getOid());
        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto, User user) {
        Seller seller = sellerRepository.findByUserOid(user.getOid())
                .orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado para o usuário: " + user.getOid()));
        sellerRepository.update(seller);
        return convertToDto(seller);
    }

    @Override
    @Transactional
    public void delete(String oid) {
        Seller seller = sellerRepository.findByUserOid(oid)
                .orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado: " + oid));
        sellerRepository.delete(seller);
    }

    @Override
    public UserType getType() {
        return UserType.SELLER;
    }

    @Override
    public List<UserDto> findAll() {
        return sellerRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public UserDto findByOid(String oid) {
        return sellerRepository.findByUserOid(oid)
                .map(this::convertToDto)
                .orElse(null);
    }

    private UserDto convertToDto(Seller seller) {
        User user = seller.getUser();
        UserDto dto = new UserDto();
        dto.setOid(user.getOid());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setUserType(UserDto.Type.SELLER);
        return dto;
    }
}
