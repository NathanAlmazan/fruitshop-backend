package com.nathanael.fruitshop.users;

import com.nathanael.fruitshop.global.ModelFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFactory implements ModelFactory<UserAccount, UserDto> {

    @Override
    public UserAccount requestToEntity(UserDto request) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(request.getFirstName());
        userAccount.setLastName(request.getLastName());
        userAccount.setEmail(request.getEmail());
        userAccount.setPhone(request.getPhone());
        userAccount.setPassword(request.getPassword());
        userAccount.setImage(request.getImage());
        userAccount.setUserPosition(UserPosition.valueOf(request.getUserPosition()));

        return userAccount;
    }

    @Override
    public UserDto entityToResponse(UserAccount entity, List<String> additionalFields) {
        UserDto userDto = new UserDto();
        userDto.setUserId(entity.getUserId());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setEmail(entity.getEmail());
        userDto.setPhone(entity.getPhone());
        userDto.setImage(entity.getImage());
        userDto.setUserPosition(entity.getUserPosition().toString());

        return userDto;
    }

    public UserAccount updateEntity(UserDto request, UserAccount entity) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setImage(request.getImage());
        entity.setUserPosition(UserPosition.valueOf(request.getUserPosition()));

        return entity;
    }
}
