package com.portmz.portsystem.controller;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getTasksForCurrentUser() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<Object> getByIdUsers(@PathVariable Long id) {
        Optional<User> resourceOptional = Optional.ofNullable(userService.getUserById(id));
        return ResponseEntity.status(HttpStatus.OK).body(resourceOptional.get());
    }








}
