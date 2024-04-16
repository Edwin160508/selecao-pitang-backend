package br.com.edwin.lima.unittests.mockito.service;

import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.controller.data.vo.mapper.UserMapper;
import br.com.edwin.lima.entity.User;
import br.com.edwin.lima.exceptions.InvalidFieldException;
import br.com.edwin.lima.repository.UserRepository;
import br.com.edwin.lima.service.UserService;
import br.com.edwin.lima.unittests.mock.MockUserCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private MockUserCar inputObject;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @BeforeEach
    public void setUp(){
        inputObject = new MockUserCar();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest(){
        User entity = inputObject.mockUserEntity(1l);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("First name 1", result.getFirstName());
        Assertions.assertEquals("Last name 1", result.getLastName());
        Assertions.assertEquals(entity.getEmail(), result.getEmail());
        Assertions.assertEquals(entity.getBirthday(), result.getBirthday());
        Assertions.assertEquals(entity.getLogin(), result.getLogin());
        Assertions.assertEquals(entity.getPassword(), result.getPassword());
        Assertions.assertEquals(entity.getPhone(), result.getPhone());
        Assertions.assertEquals(entity.getDateCreation(), result.getDateCreation());
        Assertions.assertEquals(entity.getDateLastLogin(), result.getDateLastLogin());
        Assertions.assertEquals(entity.getCars().size(), result.getCars().size());

    }

    @Test
    public void findAllTest(){
        List<User> userEntityList = inputObject.mockUserEntityList();
        Mockito.when(repository.findAllUsers()).thenReturn(userEntityList);
        List<UserVO> userVOList = UserMapper.toListVO(userEntityList);

        var results = service.findAllUsers();
        verifyUserListItens(userVOList, results);

    }

    private void verifyUserListItens(List<UserVO> userList, List<UserVO> results){
        Assertions.assertEquals(userList.size(), results.size());
        for(int i = 0; i < userList.size(); i++){
            Assertions.assertEquals(userList.get(i).getId(), results.get(i).getId());
            Assertions.assertEquals(userList.get(i).getFirstName(), results.get(i).getFirstName());
            Assertions.assertEquals(userList.get(i).getLastName(), results.get(i).getLastName());
            Assertions.assertEquals(userList.get(i).getEmail(), results.get(i).getEmail());
            Assertions.assertEquals(userList.get(i).getLogin(), results.get(i).getLogin());
            Assertions.assertEquals(userList.get(i).getPassword(), results.get(i).getPassword());
            Assertions.assertEquals(userList.get(i).getPhone(), results.get(i).getPhone());
            Assertions.assertEquals(userList.get(i).getBirthday(), results.get(i).getBirthday());
            Assertions.assertEquals(userList.get(i).getDateCreation(), results.get(i).getDateCreation());
            Assertions.assertEquals(userList.get(i).getDateLastLogin(), results.get(i).getDateLastLogin());
            Assertions.assertEquals(userList.get(i).getCars().size(), results.get(i).getCars().size());
        }
    }

    @Test
    public void deleteTest(){
        User entity = inputObject.mockUserEntity(1l);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.deleteById(1L);
    }

    @Test
    public void updateTest(){
        User userEntity = inputObject.mockUserEntity(1L);
        User userPersisted = userEntity;

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(repository.save(userEntity)).thenReturn(userPersisted);

        UserVO userVO = inputObject.mockUserVO(1L);
        var result = service.update(userVO);

        Assertions.assertNotNull(result);
    }

    @Test
    public void updateFirsNameNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setFirstName(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

    @Test
    public void updateLastNameNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setLastName(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

    @Test
    public void updateEmailNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setEmail(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

    @Test
    public void updateBirthdayNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setBirthday(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

    @Test
    public void updateLoginNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setLogin(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

    @Test
    public void updatePasswordNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setPassword(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

    @Test
    public void updatePhoneNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setPhone(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

    @Test
    public void updateCarsNullTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        userVO.setCars(null);

        Assertions.assertThrows(InvalidFieldException.class, () -> {
            service.update(userVO);
        });

    }

}
