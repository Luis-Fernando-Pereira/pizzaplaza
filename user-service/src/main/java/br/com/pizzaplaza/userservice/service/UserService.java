package br.com.pizzaplaza.userservice.service;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.enums.UserType;
import br.com.pizzaplaza.entity.systemactor.User;
import br.com.pizzaplaza.userservice.factory.UserFactory;
import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import br.com.pizzaplaza.userservice.repository.UserRepository;
import br.com.pizzaplaza.util.ValidationUtils;
import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.jspecify.annotations.NonNull;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.pizzaplaza.util.ValidationUtils.isCpfValid;
import static br.com.pizzaplaza.util.ValidationUtils.isEmailValid;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserFactory userFactory;

    @Inject
    private Instance<UserStrategy> strategies;

    private Map<UserType, UserStrategy> strategyMap;

    @PostConstruct
    void init() {
        strategyMap = new HashMap<>();
        for (UserStrategy strategy : strategies) {
            strategyMap.put(strategy.getType(), strategy);
        }
    }

    @Transactional
    public UserDto save(@Valid UserDto userDto, UserType type) {

        isUserDtoValid(userDto);
        validateUserCredentials(userDto);

        User user = userFactory.create(userDto);

        user = userRepository.save(user);

        UserStrategy strategy = getUserStrategy(type);

        return strategy.save(userDto, user);

    }

    @Transactional
    public UserDto put(@Valid UserDto userDto, UserType type) {

        isUserDtoValid(userDto);

        User user = userRepository.findByOid(userDto.getOid()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        if (cpfInUse(userDto) && !userDto.cpf.equals(user.getCpf())) {
            throw new IllegalStateException("Já existe um usuário cadastrado com esse CPF.");
        }

        if (emailInUse(userDto) && !userDto.email.equals(user.getEmail())) {
            throw new IllegalStateException("Já existe um usuário cadastrado com esse CPF.");
        }

        user.setCpf(userDto.cpf);
        user.setName(userDto.name);
        user.setEmail(userDto.email);
        user.setPassword(userDto.password);

        user = userRepository.update(user);

        UserStrategy strategy = getUserStrategy(type);

        return strategy.update(userDto, user);

    }

    public void validateUserCredentials(UserDto userDto) {
        if (emailInUse(userDto)) {
            throw new IllegalStateException("Email já está em uso.");
        }

        if (cpfInUse(userDto)) {
            throw new UnauthorizedException("Já existe um usuário cadastrado com esse CPF.");
        }
    }

    public Boolean emailInUse(UserDto dto) {
        return userRepository.isEmailInUse(dto.getEmail());
    }

    public Boolean cpfInUse(UserDto dto) {
        return userRepository.isCpfInUse(dto.getCpf());
    }

    @Transactional
    public void delete(String oid, UserType type) {

        UserStrategy strategy = getUserStrategy(type);

        strategy.delete(oid);

    }

    private void validateStrategy(UserType type, UserStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo inválido: " + type);
        }
    }

    public List<UserDto> findAll(UserType type) {

        UserStrategy strategy = getUserStrategy(type);

        return strategy.findAll();

    }

    public UserDto findByOid(UserType type, String oid) {

        UserStrategy strategy = getUserStrategy(type);

        return strategy.findByOid(oid);

    }

    private @NonNull UserStrategy getUserStrategy(UserType type) {
        UserStrategy strategy = strategyMap.get(type);

        validateStrategy(type, strategy);
        return strategy;
    }

    public void isUserDtoValid(UserDto userDto) {
        Boolean isDtoValid = true;

        if (userDto == null) {
            isDtoValid = false;
        }

        if (!isEmailValid(userDto.email)) {
            isDtoValid = false;
        }

        if (!ValidationUtils.isPasswordValid(userDto.getPassword())) {
            isDtoValid = false;
        }

        if (!isCpfValid(userDto.cpf)) {
            isDtoValid = false;
        }

        if (!isDtoValid) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }
    }
}
