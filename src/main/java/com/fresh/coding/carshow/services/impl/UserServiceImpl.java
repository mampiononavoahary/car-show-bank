package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.UserRequest;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.dtos.responses.UserSummarized;
import com.fresh.coding.carshow.exceptions.NotFoundException;
import com.fresh.coding.carshow.mappers.UserMapper;
import com.fresh.coding.carshow.repositories.UserRepository;
import com.fresh.coding.carshow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public Paginate<List<UserSummarized>> findAllUsers(Integer page, Integer perPage) {
        var pageRequest = PageRequest.of(page, perPage);
        var carsPage = userRepository.findAll(pageRequest);
        var items = carsPage.getContent().stream().map(userMapper::toResponse).toList();
        return new Paginate<>(
                items,
                carsPage.getNumber(),
                carsPage.getTotalPages(),
                carsPage.getTotalElements()
        );
    }

    @Override
    public UserSummarized updateUser(Long id, UserRequest userRequest) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setPwd(userRequest.password());
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        var savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserSummarized findUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserSummarized deleteUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.deleteById(id);
        return userMapper.toResponse(user);
    }
}
