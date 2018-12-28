package store.pengfeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.pengfeng.domain.User;
import store.pengfeng.repository.UserRepository;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c)2018
 * Author:		shisen
 * Created at:	2018/12/27 19:38
 * </pre>
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/findUserByName")
    public ResponseEntity<User> findUserByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(userRepository.findByName(name), HttpStatus.OK);
    }
}
