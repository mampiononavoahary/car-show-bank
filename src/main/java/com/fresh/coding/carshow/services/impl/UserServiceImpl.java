package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.UserRequest;
import com.fresh.coding.carshow.dtos.responses.UserResponse;
import com.fresh.coding.carshow.exceptions.NotFoundException;
import com.fresh.coding.carshow.mappers.UserMapper;
import com.fresh.coding.carshow.repositories.UserRepository;
import com.fresh.coding.carshow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream().map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) {
        var savedUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse deleteById(Long id) {
        var saverUser = this.findById(id);
        userRepository.deleteById(id);
        return saverUser;
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }
}
