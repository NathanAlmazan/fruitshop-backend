package com.nathanael.fruitshop.users;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import com.nathanael.fruitshop.global.errors.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements EntityCrudServices<UserAccount, UserDto, Long> {
    @Autowired private UserRepo userRepo;
    @Autowired private UserFactory userFactory;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDto data) {
        if (emailExists(data.getEmail())) throw new InvalidRequestException("User email is already taken.");
        if (data.getPassword() != null) data.setPassword(passwordEncoder.encode(data.getPassword()));
        UserAccount newAccount = userRepo.save(userFactory.requestToEntity(data));
        return userFactory.entityToResponse(newAccount, null);
    }

    @Override
    public UserDto update(UserDto data) {
        if (data.getPassword() != null) data.setPassword(passwordEncoder.encode(data.getPassword()));
        UserAccount account = userRepo.findById(data.getUserId())
                .map(user -> userRepo.save(userFactory.updateEntity(data, user)))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("User account does not exists.");
                });

        return userFactory.entityToResponse(account, null);
    }

    @Override
    public UserDto delete(Long id) {
        UserDto user = getDtoById(id, null);
        userRepo.deleteById(id);
        return user;
    }

    @Override
    public UserDto getDtoById(Long id, List<String> additionalFields) {
        return userFactory.entityToResponse(getById(id), additionalFields);
    }

    @Override
    public UserAccount getById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("User account does not exists.");
                });
    }

    @Override
    public boolean entityExists(Long id) {
        return userRepo.findById(id).isPresent();
    }

    public boolean emailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    public UserAccount getUserByUsername(String username) {
        return userRepo.findByEmail(username);
    }

    public UserDto getByEmail(String email) {
        UserAccount account = getUserByUsername(email);

        if (account == null) throw new EntityNotFoundException("Account does not exists.");

        return userFactory.entityToResponse(account, null);
    }

    @Override
    public List<UserDto> getAll(List<String> additionalFields) {
        return userFactory.entityListToResponse(userRepo.findAll(), additionalFields);
    }

    public UserDto resetAccount(Long id) {
        UserAccount account = userRepo.findById(id)
                .map(user -> {
                    user.setPassword(null);

                    return userRepo.save(user);
                })
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("User account does not exists.");
                });

        return userFactory.entityToResponse(account, null);
    }
}
