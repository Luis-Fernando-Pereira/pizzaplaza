package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.userservice.dtos.UserDto;
import br.com.pizzaplaza.userservice.entities.Admin;
import br.com.pizzaplaza.userservice.entities.User;
import br.com.pizzaplaza.userservice.enums.UserType;
import br.com.pizzaplaza.userservice.interfaces.ActorStrategy;
import br.com.pizzaplaza.userservice.repository.AdminRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AdminStrategy implements ActorStrategy {

    @Inject
    AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDto save(UserDto userDto, User user) {
        Admin admin = new Admin();
        admin.setUser(user);
        adminRepository.save(admin);
        userDto.setOid(user.getOid());
        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto, User user) {
        Admin admin = adminRepository.findByUserOid(user.getOid())
                .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado para o usuário: " + user.getOid()));
        adminRepository.update(admin);
        return convertToDto(admin);
    }

    @Override
    @Transactional
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
        return adminRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public UserDto findByOid(String oid) {
        return adminRepository.findByOid(oid)
                .map(this::convertToDto)
                .orElse(null);
    }

    private UserDto convertToDto(Admin admin) {
        User user = admin.getUser();
        UserDto dto = new UserDto();
        dto.setOid(user.getOid());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setUserType(UserDto.Type.ADMIN);
        return dto;
    }
}
