package br.com.edwin.lima.service;

import br.com.edwin.lima.entity.User;
import br.com.edwin.lima.exceptions.ResourceNotFoundException;
import br.com.edwin.lima.repository.CarRepository;
import br.com.edwin.lima.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<User> findAllUsers(){
        logger.info("Try list all Users.");
        return repository.findAll();
    }

    public User findById(Long id){
        logger.info("Finding a user by id "+id+".");
        Optional<User> userFounded = repository.findById(id);
        userFounded(userFounded);


        return userFounded.get();
    }

    private void userFounded(Optional<User> userEntity){
        if(!userEntity.isPresent()){
            throw new ResourceNotFoundException("No records found for this ID!");
        }
    }


}
