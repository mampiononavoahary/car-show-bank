package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.UserRequest;
import com.fresh.coding.carshow.dtos.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse deleteById(Long id);

    UserResponse update(Long id, UserRequest userRequest);
}
