package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.UserRequest;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.dtos.responses.UserSummarized;
import com.fresh.coding.carshow.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public Paginate<List<UserSummarized>> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "10") String perPage
    ){
        return userService.findAllUsers(Integer.valueOf(page), Integer.valueOf(perPage));
    }

    @PutMapping("/{id}")
    public UserSummarized getUser(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public UserSummarized updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest){
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public UserSummarized deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
}
