package br.com.edwin.lima.service;

import br.com.edwin.lima.controller.CarController;
import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.mapper.CarMapper;
import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.exceptions.ResourceNotFoundException;
import br.com.edwin.lima.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CarService {

    private Logger logger = Logger.getLogger(CarService.class.getName());

    @Autowired
    private CarRepository repository;

    public List<CarVO> findAll(){
        logger.info("Try list all Cars.");
        var carListVOs = CarMapper.toListVO(repository.findAll());
        carListVOs.stream().forEach(carVO -> addSelfRefHateoas(carVO));
        return carListVOs;
    }

    public CarVO findById(Long id){
        logger.info("Search a car by id "+id+".");
        Optional<Car> carFounded = repository.findById(id);
        carFounded(carFounded);

        var carFoundedVO = CarMapper.toVO(carFounded.get());
        addSelfRefHateoas(carFoundedVO);
        return carFoundedVO;
    }

    private void carFounded(Optional<Car> carEntity){
        if(!carEntity.isPresent()){
            throw new ResourceNotFoundException("No records found for this ID!");
        }
    }

    private void addSelfRefHateoas(CarVO carVO){
        var withSelfRef = linkTo(methodOn(CarController.class).findById(carVO.getKey())).withSelfRel();
        carVO.add(withSelfRef);
    }
}
