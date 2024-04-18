package br.com.edwin.lima.controller;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
