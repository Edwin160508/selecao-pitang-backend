package br.com.edwin.lima.service;

import br.com.edwin.lima.controller.UserController;
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
import br.com.edwin.lima.utils.EncoderUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {
    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private CarRepository carRepository;

    public UserService(UserRepository repository, CarRepository carRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
    }

    @Transactional
    public List<UserVO> findAllUsers(){
        logger.info("List all Users.");
        var userListVOs = UserMapper.toListVO(repository.findAllUsers());
        userListVOs.stream().forEach(userVO -> addSelfRefHateoas(userVO));
        return userListVOs;
    }

    public UserVO findById(Long id){
        logger.info("Search a user by id "+id+".");
        Optional<User> userFounded = repository.findById(id);
        userFounded(userFounded);

        var userFoundedVO = UserMapper.toVO(userFounded.get());
        addSelfRefHateoas(userFoundedVO);
        return userFoundedVO;
    }

    public UserVO update(UserVO vo) {
        logger.info("Update a user by "+vo.getKey()+".");
        validateFieldsVO(vo);
        Optional<User> userEntity = repository.findById(vo.getKey());
        userFounded(userEntity);

        try {
            if(!userEntity.get().getPassword().equals(vo.getPassword())){
                String encriptyPassword = EncoderUtil.encodePassword(vo.getPassword()).substring("{pbkdf2}".length());
                userEntity.get().setPassword(encriptyPassword);
            }
            userEntity.get().setFirstName(vo.getFirstName());
            userEntity.get().setLastName(vo.getLastName());
            userEntity.get().setEmail(vo.getEmail());
            userEntity.get().setLogin(vo.getLogin());
            userEntity.get().setPhone(vo.getPhone());
            userEntity.get().setCars(CarMapper.toListEntity(vo.getCars()));
            userEntity.get().setBirthday(DateUtil.convertStringtoDate(vo.getBirthdayString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        var userSavedVO = UserMapper.toVO(repository.save(userEntity.get()));
        addSelfRefHateoas(userSavedVO);
        return userSavedVO;
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
            u.setKey(1L);
            c.setUser(u);
        }
        String encriptyPassword = EncoderUtil.encodePassword(vo.getPassword()).substring("{pbkdf2}".length());
        vo.setPassword(encriptyPassword);
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

        var userSavedVO = UserMapper.toVO(userSaved);
        addSelfRefHateoas(userSavedVO);
        return userSavedVO;
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

    private void addSelfRefHateoas(UserVO userVO){
        var withSelfRef = linkTo(methodOn(UserController.class).findById(userVO.getKey())).withSelfRel();
        userVO.add(withSelfRef);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.info("Finding one user by name " + login + "!");
        var user = repository.findUserByLogin(login);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + login + " not found!");
        }
    }
}
