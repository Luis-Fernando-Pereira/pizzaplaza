package br.com.pizzaplaza.userservice.service;

import br.com.pizzaplaza.userservice.dtos.UserDto;
import br.com.pizzaplaza.userservice.enums.UserType;
import br.com.pizzaplaza.userservice.entities.User;
import br.com.pizzaplaza.userservice.factory.UserFactory;
import br.com.pizzaplaza.userservice.interfaces.ActorStrategy;
import br.com.pizzaplaza.userservice.repository.UserRepository;
import br.com.pizzaplaza.userservice.util.PasswordUtil;
import br.com.pizzaplaza.userservice.util.ValidationUtils;
import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserFactory userFactory;

    @Inject
    Instance<ActorStrategy> strategies;

    private Map<UserType, ActorStrategy> strategyMap;

    @PostConstruct
    void init() {
        strategyMap = new HashMap<>();
        for (ActorStrategy strategy : strategies) {
            strategyMap.put(strategy.getType(), strategy);
        }
    }

    @Transactional
    public UserDto save(UserDto userDto, UserType type) {
        isUserDtoValid(userDto);
        validateUserCredentials(userDto);

        User user = userFactory.create(userDto);
        user = userRepository.save(user);

        ActorStrategy strategy = getUserStrategy(type);
        return strategy.save(userDto, user);
    }

    @Transactional
    public UserDto put(UserDto userDto, UserType type) {
        if (userDto == null) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }

        User user = userRepository.findByOid(userDto.getOid())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        if (userRepository.isCpfInUse(userDto.getCpf()) && !userDto.getCpf().equals(user.getCpf())) {
            throw new IllegalStateException("Já existe um usuário cadastrado com esse CPF.");
        }

        if (userRepository.isEmailInUse(userDto.getEmail()) && !userDto.getEmail().equals(user.getEmail())) {
            throw new IllegalStateException("Já existe um usuário cadastrado com esse email.");
        }

        user.setCpf(userDto.getCpf());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            user.setPassword(PasswordUtil.hash(userDto.getPassword()));
        }

        user = userRepository.update(user);

        ActorStrategy strategy = getUserStrategy(type);
        return strategy.update(userDto, user);
    }

    public void validateUserCredentials(UserDto userDto) {
        if (userRepository.isEmailInUse(userDto.getEmail())) {
            throw new IllegalStateException("Email já está em uso.");
        }

        if (userRepository.isCpfInUse(userDto.getCpf())) {
            throw new IllegalStateException("Já existe um usuário cadastrado com esse CPF.");
        }
    }

    @Transactional
    public void delete(String oid, UserType type) {
        ActorStrategy strategy = getUserStrategy(type);
        strategy.delete(oid);
    }

    public List<UserDto> findAll(UserType type) {
        ActorStrategy strategy = getUserStrategy(type);
        return strategy.findAll();
    }

    public UserDto findByOid(UserType type, String oid) {
        ActorStrategy strategy = getUserStrategy(type);
        return strategy.findByOid(oid);
    }

    private ActorStrategy getUserStrategy(UserType type) {
        ActorStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo inválido: " + type);
        }
        return strategy;
    }

    public void isUserDtoValid(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }

        boolean valid = ValidationUtils.isEmailValid(userDto.getEmail())
                && ValidationUtils.isPasswordValid(userDto.getPassword())
                && ValidationUtils.isCpfValid(userDto.getCpf());

        if (!valid) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }
    }
}
