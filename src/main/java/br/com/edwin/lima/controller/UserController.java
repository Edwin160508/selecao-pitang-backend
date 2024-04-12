package br.com.edwin.lima.controller;

import br.com.edwin.lima.entity.User;
import br.com.edwin.lima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> listAllUsers(){
        return service.findAllUsers();
    }

}
