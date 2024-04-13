package br.com.edwin.lima.service;

import br.com.edwin.lima.controller.data.vo.UserVO;
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
@Transactional
public class UserService {
    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private CarRepository carRepository;

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

    public UserVO update(User user){
        logger.info("Update a user by "+user.getId()+".");
        Optional<User> userEntity = repository.findById(user.getId());
        userFounded(userEntity);

        userEntity.get().setFirstName(user.getFirstName());
        userEntity.get().setLastName(user.getLastName());
        userEntity.get().setEmail(user.getEmail());
        userEntity.get().setBirthday(user.getBirthday());
        userEntity.get().setLogin(user.getLogin());
        userEntity.get().setPassword(user.getPassword());
        userEntity.get().setPhone(user.getPhone());
        userEntity.get().setDateLastLogin(new Date());
        userEntity.get().setCars(user.getCars());

        return UserMapper.toVO(repository.save(userEntity.get()));
    }

    public void deleteById(Long id){
        logger.info("Delete a user by "+id+".");
        Optional<User> userEntity = repository.findById(id);
        userFounded(userEntity);
        repository.delete(userEntity.get());
    }
    public UserVO save(User user){

        if(!user.getCars().isEmpty()) {
            for (Car c : user.getCars()) {
                User u = new User();
                u.setId(1L);
                c.setUser(u);
            }
        }//else{throw new EmptyCars}
        User userSaved = repository.save(user);
        for (Car c : user.getCars()) {
            c.setUser(userSaved);
            carRepository.save(c);
        }
        userSaved.setCars(user.getCars());
        repository.save(userSaved);

        return UserMapper.toVO(userSaved);
    }

    private void userFounded(Optional<User> userEntity){
        if(!userEntity.isPresent()){
            throw new ResourceNotFoundException("No records found for this ID!");
        }
    }


}
