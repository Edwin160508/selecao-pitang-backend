package br.com.edwin.lima.service;

import br.com.edwin.lima.controller.CarController;
import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.mapper.CarMapper;
import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.exceptions.InvalidFieldException;
import br.com.edwin.lima.exceptions.ResourceNotFoundException;
import br.com.edwin.lima.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
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

    public void deleteById(Long id) {
        logger.info("Delete a car by "+id+".");
        Optional<Car> carEntity = repository.findById(id);
        carFounded(carEntity);
        repository.delete(carEntity.get());
    }

    private void validateFieldsVO(CarVO vo){
        if(Objects.isNull(vo.getColor()) || vo.getColor().isEmpty()){
            throw new InvalidFieldException("Color field is required!");
        }
        if(Objects.isNull(vo.getModel()) || vo.getModel().isEmpty()){
            throw new InvalidFieldException("Model field is required!");
        }
        if(Objects.isNull(vo.getLicensePlate()) || vo.getLicensePlate().isEmpty()){
            throw new InvalidFieldException("License Plate field is required!");
        }
        if(Objects.isNull(vo.getUser())){
            throw new InvalidFieldException("User field is required!");
        }
        if(Objects.isNull(vo.getYear()) || vo.getYear() == 0){
            throw new InvalidFieldException("Year field is required!");
        }
    }
    public CarVO update(CarVO vo) {
        logger.info("Update a car by id "+vo.getKey()+".");
        validateFieldsVO(vo);
        Optional<Car> carEntity = repository.findById(vo.getKey());
        carFounded(carEntity);
        carEntity.get().setModel(vo.getModel());
        carEntity.get().setYear(vo.getYear());
        carEntity.get().setLicensePlate(vo.getLicensePlate());
        carEntity.get().setColor(vo.getColor());

        CarVO carVOUpdated = CarMapper.toVO(repository.save(carEntity.get()));
        addSelfRefHateoas(carVOUpdated);
        return carVOUpdated;
    }

    public CarVO save(CarVO vo){
        logger.info("Save a car.");
        validateFieldsVO(vo);
        Car carSaved = null;
        try {
            carSaved = repository.save(CarMapper.toEntity(vo));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        CarVO carVOSaved = CarMapper.toVO(carSaved);
        addSelfRefHateoas(carVOSaved);
        return carVOSaved;
    }
}
