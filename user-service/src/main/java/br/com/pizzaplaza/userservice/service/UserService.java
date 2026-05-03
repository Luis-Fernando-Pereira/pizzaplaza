package br.com.pizzaplaza.userservice.service;

import br.com.pizzaplaza.userservice.interfaces.UserStrategy;
import br.com.pizzaplaza.entity.dto.UserDto;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    private UserStrategy strategy;

    @Transactional
    public UserDto save(UserDto userDto, UserStrategy strategy) throws Exception {

        strategy = strategy;

        if (userTypeNotSupported(userDto)) {
            throw new UnauthorizedException();
        }

        return strategy.save(userDto);

    }

    @Transactional
    public List<UserDto> findAll(UserStrategy strategy) throws Exception {

        return strategy.findAll();

    }

    @Transactional
    public UserDto findByOid(UserStrategy strategy, String oid) throws Exception {

        return strategy.findByOid(oid);

    }

    private Boolean userTypeNotSupported(UserDto dto) {
        return !strategy.supports(dto.userType.name());
    }

}
