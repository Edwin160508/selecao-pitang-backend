package br.com.edwin.lima.controller;

import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for Managing Users")
public class UserController {


    @Autowired
    private UserService service;


    @GetMapping
    @Operation(summary = "Finds all Users", description = "Finds all Users",
            tags = "Users",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )})
            })
    ResponseEntity<List<UserVO>> listAllUsers(){
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a User", description = "Find a User by id",
            tags = "Users",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )})
            })
    public ResponseEntity<UserVO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Save a User", description = "Save a User and your cars",
            tags = "Users",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )})
            })
    ResponseEntity<UserVO> saveUser(@RequestBody UserVO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping
    @Operation(summary = "Update a User", description = "Update a User by id",
            tags = "Users",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )})
            })
    ResponseEntity<UserVO> updateUser(@RequestBody UserVO user){
        return ResponseEntity.ok(service.update(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a User", description = "Delete a User by id",
            tags = "Users",
            responses = {
                    @ApiResponse(
                            description = "No content",
                            responseCode = "204",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserVO.class)) )})
            })
    ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
