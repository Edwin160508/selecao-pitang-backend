package br.com.edwin.lima.unittests.mock;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.entity.Car;
import br.com.edwin.lima.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockUserCar {
    /* Entities Mocks */
    public User mockUserEntity(Long id){
        User entity = new User();
        entity.setFirstName("First name "+id);
        entity.setLastName("Last name "+id);
        entity.setBirthday(new Date());
        entity.setEmail("email@email.com.br");
        entity.setPhone("(99)99999-9999");
        entity.setDateLastLogin(new Date());
        entity.setDateCreation(new Date());
        entity.setLogin("Login"+id);
        entity.setPassword("ajsifjas");
        entity.setCars(mockCarEntityList(id));
        entity.setId(id);
        return entity;
    }

    public List<Car> mockCarEntityList(Long id){
        List<Car> carList = new ArrayList<>();
        for(long index=0; index<9; index++){
            Car car = mockCarEntity(id);
            carList.add(car);
        }

        return carList;
    }

    public List<User> mockUserEntityList(){
        List<User> userEntityList = new ArrayList<>();
        for(long index=0; index<9; index++){
            User vo = mockUserEntity(index);
            userEntityList.add(vo);
        }

        return userEntityList;
    }

    public Car mockCarEntity(Long id){
        Car entity = new Car();
        entity.setId(id);
        entity.setColor("Color "+id);
        entity.setYear(2024);
        entity.setModel("Model "+id);
        entity.setLicensePlate("ABC12"+id);

        User user = new User();
        user.setId(id);
        entity.setUser(user);
        return entity;
    }

    /* VOs Mocks */
    public UserVO mockUserVO(Long id){
        UserVO vo = new UserVO();
        vo.setFirstName("First name "+id);
        vo.setLastName("Last name "+id);
//        vo.setBirthday(new Date());
        vo.setBirthdayString("1988-09-01");
        vo.setEmail("email@email.com.br");
        vo.setPhone("(99)99999-9999");
        vo.setDateLastLogin(new Date());
        vo.setDateCreation(new Date());
        vo.setLogin("Login"+id);
        vo.setPassword("ajsifjas");
        vo.setCars(mockCarVOList(id));
        vo.setKey(id);
        return vo;
    }

    public List<CarVO> mockCarVOList(Long id){
        List<CarVO> carList = new ArrayList<>();
        for(long index=0; index<9; index++){
            CarVO car = mockCarVO(id);

            carList.add(car);
        }

        return carList;
    }

    public CarVO mockCarVO(Long id){
        CarVO vo = new CarVO();
        vo.setKey(id);
        vo.setColor("Color "+id);
        vo.setYear(2024);
        vo.setModel("Model "+id);
        vo.setLicensePlate("ABC12"+id);

        UserVO user = new UserVO();
        user.setKey(id);
        vo.setUser(user);
        return vo;
    }
}
