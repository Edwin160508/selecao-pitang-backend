package br.com.edwin.lima.service;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.mapper.CarMapper;
import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CarService {

    private Logger logger = Logger.getLogger(CarService.class.getName());

    @Autowired
    private CarRepository repository;

    public List<CarVO> findAll(){
        logger.info("Try list all Cars.");
        return CarMapper.toListVO(repository.findAll());
    }
}
