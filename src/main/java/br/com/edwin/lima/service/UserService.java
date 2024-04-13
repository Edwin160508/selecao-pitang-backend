package br.com.edwin.lima.service;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.controller.data.vo.mapper.CarMapper;
import br.com.edwin.lima.controller.data.vo.mapper.UserMapper;
import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.entity.User;
import br.com.edwin.lima.exceptions.ResourceNotFoundException;
import br.com.edwin.lima.repository.CarRepository;
import br.com.edwin.lima.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private CarRepository carRepository;
    @Transactional
    public List<UserVO> findAllUsers(){
        logger.info("List all Users.");
        return UserMapper.toListVO(repository.findAll());
    }

    public UserVO findById(Long id){
        logger.info("Search a user by id "+id+".");
        Optional<User> userFounded = repository.findById(id);
        userFounded(userFounded);


        return UserMapper.toVO(userFounded.get());
    }

    public UserVO update(UserVO vo){
        logger.info("Update a user by "+vo.getId()+".");
        Optional<User> userEntity = repository.findById(vo.getId());
        userFounded(userEntity);

        userEntity.get().setFirstName(vo.getFirstName());
        userEntity.get().setLastName(vo.getLastName());
        userEntity.get().setEmail(vo.getEmail());
        userEntity.get().setBirthday(vo.getBirthday());
        userEntity.get().setLogin(vo.getLogin());
        userEntity.get().setPassword(vo.getPassword());
        userEntity.get().setPhone(vo.getPhone());
        userEntity.get().setDateLastLogin(new Date());
        userEntity.get().setCars(CarMapper.toListEntity(vo.getCars()));


        return UserMapper.toVO(repository.save(userEntity.get()));
    }

    public void deleteById(Long id){
        logger.info("Delete a user by "+id+".");
        Optional<User> userEntity = repository.findById(id);
        userFounded(userEntity);
        repository.delete(userEntity.get());
    }
    public UserVO save(UserVO vo){

        if(!vo.getCars().isEmpty()) {
            for (CarVO c : vo.getCars()) {
                UserVO u = new UserVO();
                u.setId(1L);
                c.setUser(u);
            }
        }
        vo.setDateCreation(new Date());//set current dateCreation
        User userSaved = repository.save(UserMapper.toEntity(vo));
        for(Car c: userSaved.getCars()){
            c.setUser(userSaved);
            carRepository.save(c);
        }

        return UserMapper.toVO(userSaved);
    }

    private void userFounded(Optional<User> userEntity){
        if(!userEntity.isPresent()){
            throw new ResourceNotFoundException("No records found for this ID!");
        }
    }


}
