package br.com.pizzaplaza.userservice.strategies;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.enums.UserType;
import br.com.pizzaplaza.entity.systemactor.Client;
import br.com.pizzaplaza.entity.systemactor.User;
import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import br.com.pizzaplaza.userservice.repository.ClientRepository;
import br.com.pizzaplaza.userservice.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClientStrategy implements UserStrategy {

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
    public void delete(String oid) {
        Client client = clientRepository.findByOid(oid)
                .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado: " + oid));

        clientRepository.delete(client);
    }

    @Override
    public UserDto update(UserDto userDto, User user) {
        Client client = clientRepository.findByUserOid(user.getOid())
                .orElseThrow(() -> new IllegalArgumentException("Admin não encontrado: " + userDto.getOid()));

        client = clientRepository.update(client);

        return convertClientToUserDto(client);
    }

    @Override
    public UserType getType() {
        return UserType.CLIENT;
    }

    @Override
    public List<UserDto> findAll() {

        List<Client> results = clientRepository.findAll();

        return results.stream()
                .map(this::convertClientToUserDto)
                .toList();
    }

    @Override
    public UserDto findByOid(String oid) {
        return clientRepository.findByOid(oid)
                .map(this::convertClientToUserDto)
                .orElse(null);
    }

    private UserDto convertClientToUserDto(Client client) {
        User user = client.getUser();
        UserDto dto = new UserDto();

        dto.setOid(user.getOid());
        dto.setEmail(user.getEmail());
        dto.setCpf(user.getCpf());
        dto.setName(user.getName());
        dto.setUserType(UserDto.Type.CLIENT);

        return dto;
    }

}
