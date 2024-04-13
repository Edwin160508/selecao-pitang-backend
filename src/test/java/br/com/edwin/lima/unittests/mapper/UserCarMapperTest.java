package br.com.edwin.lima.unittests.mapper;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.controller.data.vo.mapper.UserMapper;
import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.entity.User;
import br.com.edwin.lima.unittests.mock.MockUserCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserCarMapperTest {

    MockUserCar inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockUserCar();
    }
    @Test
    public void mapperUserCarEtityToVOTest(){
        User user = inputObject.mockUserEntity(1L);
        UserVO userVO = UserMapper.toVO(user);
        verifyCarListItens(user.getCars(), userVO.getCars());
        Assertions.assertEquals(user.getId(), userVO.getId());
        Assertions.assertEquals(user.getFirstName(), userVO.getFirstName());
        Assertions.assertEquals(user.getLastName(), userVO.getLastName());
        Assertions.assertEquals(user.getBirthday(), userVO.getBirthday());
        Assertions.assertEquals(user.getEmail(), userVO.getEmail());
        Assertions.assertEquals(user.getPhone(), userVO.getPhone());
        Assertions.assertEquals(user.getLogin(), userVO.getLogin());
        Assertions.assertEquals(user.getPassword(), userVO.getPassword());
        Assertions.assertEquals(user.getDateCreation(), userVO.getDateCreation());
        Assertions.assertEquals(user.getDateLastLogin(), userVO.getDateLastLogin());
    }

    private void verifyCarListItens(List<Car> carEntityList, List<CarVO> carVOList){
        Assertions.assertEquals(carEntityList.size(), carVOList.size());
        for(int i = 0; i < carVOList.size(); i++){
            Assertions.assertEquals(carVOList.get(i).getId(), carEntityList.get(i).getId());
            Assertions.assertEquals(carVOList.get(i).getYear(), carEntityList.get(i).getYear());
            Assertions.assertEquals(carVOList.get(i).getLicensePlate(), carEntityList.get(i).getLicensePlate());
            Assertions.assertEquals(carVOList.get(i).getColor(), carEntityList.get(i).getColor());
            Assertions.assertEquals(carVOList.get(i).getModel(), carEntityList.get(i).getModel());
        }
    }

    @Test
    public void mapperUserCarVOToEntityTest(){
        UserVO userVO = inputObject.mockUserVO(1L);
        User user = UserMapper.toEntity(userVO);
        verifyCarListItens(user.getCars(), userVO.getCars());
        Assertions.assertEquals(userVO.getId(), user.getId());
        Assertions.assertEquals(userVO.getFirstName(), user.getFirstName());
        Assertions.assertEquals(userVO.getLastName(), user.getLastName());
        Assertions.assertEquals(userVO.getBirthday(), user.getBirthday());
        Assertions.assertEquals(userVO.getEmail(), user.getEmail());
        Assertions.assertEquals(userVO.getPhone(), user.getPhone());
        Assertions.assertEquals(userVO.getLogin(), user.getLogin());
        Assertions.assertEquals(userVO.getPassword(), user.getPassword());
        Assertions.assertEquals(userVO.getDateCreation(), user.getDateCreation());
        Assertions.assertEquals(userVO.getDateLastLogin(), user.getDateLastLogin());
    }
}
