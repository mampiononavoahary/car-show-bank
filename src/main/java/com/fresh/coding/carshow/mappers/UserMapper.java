package com.fresh.coding.carshow.mappers;

import com.fresh.coding.carshow.dtos.requests.UserRequest;
import com.fresh.coding.carshow.dtos.responses.UserResponse;
import com.fresh.coding.carshow.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest userRequest){
        return (new User())
                .setEmail(userRequest.email())
                .setPwd(userRequest.password())
                .setName(userRequest.name())
                .setId(userRequest.id());
    }

    public UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }

}
