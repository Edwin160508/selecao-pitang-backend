package br.com.edwin.lima.service;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.controller.data.vo.mapper.CarMapper;
import br.com.edwin.lima.controller.data.vo.mapper.UserMapper;
import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.entity.User;
import br.com.edwin.lima.exceptions.InvalidFieldException;
import br.com.edwin.lima.exceptions.ResourceNotFoundException;
import br.com.edwin.lima.repository.CarRepository;
import br.com.edwin.lima.repository.UserRepository;
import br.com.edwin.lima.utils.DateUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
        return UserMapper.toListVO(repository.findAllUsers());
    }

    public UserVO findById(Long id){
        logger.info("Search a user by id "+id+".");
        Optional<User> userFounded = repository.findById(id);
        userFounded(userFounded);


        return UserMapper.toVO(userFounded.get());
    }

    public UserVO update(UserVO vo) {
        logger.info("Update a user by "+vo.getId()+".");
        validateFieldsVO(vo);
        Optional<User> userEntity = repository.findById(vo.getId());
        userFounded(userEntity);

        try {
            userEntity.get().setFirstName(vo.getFirstName());
            userEntity.get().setLastName(vo.getLastName());
            userEntity.get().setEmail(vo.getEmail());
            userEntity.get().setLogin(vo.getLogin());
            userEntity.get().setPassword(vo.getPassword());
            userEntity.get().setPhone(vo.getPhone());
            userEntity.get().setCars(CarMapper.toListEntity(vo.getCars()));
            userEntity.get().setBirthday(DateUtil.convertStringtoDate(vo.getBirthdayString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return UserMapper.toVO(repository.save(userEntity.get()));
    }

    public void deleteById(Long id){
        logger.info("Delete a user by "+id+".");
        Optional<User> userEntity = repository.findById(id);
        userFounded(userEntity);
        repository.delete(userEntity.get());
    }
    public UserVO save(UserVO vo){
        logger.info("Save a user.");
        validateFieldsVO(vo);
        for (CarVO c : vo.getCars()) {
            UserVO u = new UserVO();
            u.setId(1L);
            c.setUser(u);
        }

        vo.setDateCreation(new Date());//set current dateCreation
        User userSaved = null;
        try {
            userSaved = repository.save(UserMapper.toEntity(vo));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for(Car c: userSaved.getCars()){
            c.setUser(userSaved);
            carRepository.save(c);
        }

        return UserMapper.toVO(userSaved);
    }

    private void validateFieldsVO(UserVO vo){
        logger.info("Validate fieds comming to VO user.");
        if(vo.getFirstName() == null || vo.getFirstName().isEmpty()){
            throw new InvalidFieldException("First Name field is required!");
        }
        if(vo.getLastName() == null || vo.getLastName().isEmpty()){
            throw new InvalidFieldException("Last Name field is required!");
        }
        if(vo.getEmail() == null || vo.getEmail().isEmpty()){
            throw new InvalidFieldException("E-mail field is required!");
        }
        if(vo.getBirthdayString() == null){
            throw new InvalidFieldException("Birthday field is required!");
        }
        if(vo.getLogin() == null || vo.getLogin().isEmpty()){
            throw new InvalidFieldException("Login field is required!");
        }
        if(vo.getPassword() == null || vo.getPassword().isEmpty()){
            throw new InvalidFieldException("Password field is required!");
        }
        if(vo.getPhone() == null || vo.getPhone().isEmpty()){
            throw new InvalidFieldException("Phone field is required!");
        }
        if(vo.getCars() == null || vo.getCars().size()==0){
            throw new InvalidFieldException("Car fields is required!");
        }
    }

    private void userFounded(Optional<User> userEntity){
        if(!userEntity.isPresent()){
            throw new ResourceNotFoundException("No records found for this ID!");
        }
    }


}
