package br.com.edwin.lima.controller;

import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService service;


    @GetMapping
    ResponseEntity<List<UserVO>> listAllUsers(){
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{id}")
    ResponseEntity<UserVO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    ResponseEntity<UserVO> saveUser(@RequestBody UserVO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping
    ResponseEntity<UserVO> updateUser(@RequestBody UserVO user){
        return ResponseEntity.ok(service.update(user));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
