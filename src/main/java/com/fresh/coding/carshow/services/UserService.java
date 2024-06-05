package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.UserRequest;
import com.fresh.coding.carshow.dtos.responses.UserSummarized;

import java.util.List;

public interface UserService {
    List<UserSummarized> findAllUsers();

    UserSummarized updateUser(Long id, UserRequest userRequest);

    UserSummarized findUserById(Long id);

    UserSummarized deleteUserById(Long id);
}
