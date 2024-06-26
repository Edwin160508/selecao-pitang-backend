package br.com.edwin.lima.controller.data.vo.mapper;

import br.com.edwin.lima.controller.data.vo.UserVO;
import br.com.edwin.lima.entity.User;
import br.com.edwin.lima.utils.DateUtil;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserVO toVO(User entity){
        UserVO vo = new UserVO();
        vo.setFirstName(entity.getFirstName());
        vo.setLastName(entity.getLastName());
        vo.setBirthdayString(DateUtil.convertDateToString(entity.getBirthday()));
        vo.setEmail(entity.getEmail());
        vo.setPhone(entity.getPhone());
        vo.setDateLastLogin(entity.getDateLastLogin());
        vo.setDateCreation(entity.getDateCreation());
        vo.setCars(CarMapper.toListVO(entity.getCars()));
        vo.setLogin(entity.getLogin());
        vo.setPassword(entity.getPassword());
        vo.setKey(entity.getId());
        return vo;
    }

    public static User toEntity(UserVO vo) throws ParseException {
        User entity = new User();
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        if(Objects.nonNull(vo.getBirthdayString())) {
            entity.setBirthday(DateUtil.convertStringtoDate(vo.getBirthdayString()));
        }
        entity.setEmail(vo.getEmail());
        entity.setPhone(vo.getPhone());
        entity.setDateLastLogin(vo.getDateLastLogin());
        entity.setDateCreation(vo.getDateCreation());
        entity.setLogin(vo.getLogin());
        entity.setPassword(vo.getPassword());
        if(Objects.nonNull(vo.getCars())) {
            entity.setCars(CarMapper.toListEntity(vo.getCars()));
        }
        entity.setId(vo.getKey());
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);
        return entity;
    }

    public static List<UserVO> toListVO(List<User> entityListC){
        return entityListC.stream()
                .map(entity -> toVO(entity))
                .collect(Collectors.toList());
    }
}
