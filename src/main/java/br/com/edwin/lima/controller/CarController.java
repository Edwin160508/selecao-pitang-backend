package br.com.edwin.lima.controller;

import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> listAllCars(){
        return service.findAll();
    }
}
