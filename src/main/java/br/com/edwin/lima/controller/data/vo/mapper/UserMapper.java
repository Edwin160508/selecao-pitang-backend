package br.com.edwin.lima.controller.data.vo.mapper;

import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.entity.User;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserVO toVO(User entity){
        UserVO vo = new UserVO();
        vo.setFirstName(entity.getFirstName());
        vo.setLastName(entity.getLastName());
        vo.setBirthday(entity.getBirthday());
        vo.setEmail(entity.getEmail());
        vo.setPhone(entity.getPhone());
        vo.setDateLastLogin(entity.getDateLastLogin());
        vo.setDateCreation(entity.getDateCreation());
        vo.setCars(CarMapper.toListVO(entity.getCars()));
        vo.setLogin(entity.getLogin());
        vo.setPassword(entity.getPassword());
        vo.setId(entity.getId());
        return vo;
    }

    public static User toEntity(UserVO vo){
        User entity = new User();
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setBirthday(vo.getBirthday());
        entity.setEmail(vo.getEmail());
        entity.setPhone(vo.getPhone());
        entity.setDateLastLogin(vo.getDateLastLogin());
        entity.setCars(CarMapper.toListEntity(vo.getCars()));
        entity.setId(vo.getId());
        return entity;
    }

    public static List<UserVO> toListVO(List<User> entityListC){
        return entityListC.stream()
                .map(entity -> toVO(entity))
                .collect(Collectors.toList());
    }
}
