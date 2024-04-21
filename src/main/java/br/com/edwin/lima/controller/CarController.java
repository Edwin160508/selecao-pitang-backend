package br.com.edwin.lima.controller;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@Tag(name = "Cars", description = "Endpoints for Managing Cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    @Operation(summary = "Finds all Cars", description = "Finds all Cars",
            tags = "Cars",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )})
            })
    public ResponseEntity<List<CarVO>> listAllCars(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Car", description = "Finds a Car by id",
            tags = "Cars",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )})
            })
    public ResponseEntity<CarVO> findById(@PathVariable(value = "id") Long key){
        return ResponseEntity.ok(service.findById(key));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a Car", description = "Remove a Car by id",
            tags = "Cars",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )})
            })
    public ResponseEntity remove(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Update a Car", description = "Update a Car by id",
            tags = "Cars",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )}),
                    @ApiResponse(
                            description = "Internal Error",
                            responseCode = "500",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CarVO.class)) )})
            })
    public ResponseEntity<CarVO> updateCar(@RequestBody CarVO carVO){
        return ResponseEntity.ok(service.update(carVO));
    }

    @PostMapping
    @Operation(summary = "Save a Car", description = "Save a Car.",
            tags = "Cars",
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
    public ResponseEntity<CarVO> saveCar(@RequestBody CarVO carVO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(carVO));
    }
}
