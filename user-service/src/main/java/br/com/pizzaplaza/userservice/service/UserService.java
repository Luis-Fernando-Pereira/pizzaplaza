package br.com.pizzaplaza.userservice.service;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.enums.UserType;
import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class UserService {

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
    public UserDto save(UserDto userDto, UserType type) {

        UserStrategy strategy = getUserStrategy(type);

        return strategy.save(userDto);

    }

    @Transactional
    public URI createUri(UserDto userDto) {
        return URI.create("/users/" + userDto.getOid());
    }

    private void validateStrategy(UserType type, UserStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo inválido: " + type);
        }
    }

    public List<UserDto> findAll(UserType type) throws Exception {

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
}
