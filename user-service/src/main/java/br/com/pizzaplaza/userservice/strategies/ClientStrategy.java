package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.userservice.dtos.UserDto;
import br.com.pizzaplaza.userservice.entities.Client;
import br.com.pizzaplaza.userservice.entities.User;
import br.com.pizzaplaza.userservice.enums.UserType;
import br.com.pizzaplaza.userservice.interfaces.ActorStrategy;
import br.com.pizzaplaza.userservice.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClientStrategy implements ActorStrategy {

    @Inject
    ClientRepository clientRepository;

    @Override
    @Transactional
    public UserDto save(UserDto userDto, User user) {
        Client client = new Client();
        client.setUser(user);
        clientRepository.save(client);
        userDto.setOid(user.getOid());
        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto, User user) {
        Client client = clientRepository.findByUserOid(user.getOid())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado para o usuário: " + user.getOid()));
        clientRepository.update(client);
        return convertToDto(client);
    }

    @Override
    @Transactional
    public void delete(String oid) {
        Client client = clientRepository.findByUserOid(oid)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + oid));
        clientRepository.delete(client);
    }

    @Override
    public UserType getType() {
        return UserType.CLIENT;
    }

    @Override
    public List<UserDto> findAll() {
        return clientRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public UserDto findByOid(String oid) {
        return clientRepository.findByUserOid(oid)
                .map(this::convertToDto)
                .orElse(null);
    }

    private UserDto convertToDto(Client client) {
        User user = client.getUser();
        UserDto dto = new UserDto();
        dto.setOid(user.getOid());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setUserType(UserDto.Type.CLIENT);
        return dto;
    }
}
