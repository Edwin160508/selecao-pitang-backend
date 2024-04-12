package br.com.edwin.lima.service;

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

    public List<User> findAllUsers(){
        logger.info("List all Users.");
        return repository.findAll();
    }

    public User findById(Long id){
        logger.info("Search a user by id "+id+".");
        Optional<User> userFounded = repository.findById(id);
        userFounded(userFounded);


        return userFounded.get();
    }

    public User update(User user){
        logger.info("Update a user by "+user.getId()+".");
        User userEntity = findById(user.getId());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setBirthday(user.getBirthday());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhone(user.getPhone());
        userEntity.setDateLastLogin(new Date());
        userEntity.setCars(user.getCars());

        return repository.save(userEntity);
    }

    public void deleteById(Long id){
        logger.info("Delete a user by "+id+".");
        User userEntity = findById(id);
        repository.delete(userEntity);
    }
    public User save(User user){

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

        return userSaved;
    }

    private void userFounded(Optional<User> userEntity){
        if(!userEntity.isPresent()){
            throw new ResourceNotFoundException("No records found for this ID!");
        }
    }


}
