package br.com.edwin.lima.controller.data.vo.mapper;

import br.com.edwin.lima.controller.data.vo.CarVO;
import br.com.edwin.lima.entity.Car;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarMapper {

    public static CarVO toVO(Car entity){
        CarVO vo = new CarVO();
        vo.setId(entity.getId());
        vo.setYear(entity.getYear());
        vo.setColor(entity.getColor());
        vo.setLicensePlate(entity.getLicensePlate());
        vo.setModel(entity.getModel());
        vo.setUser(vo.getUser());

        return vo;
    }

    public static Car toEntity(CarVO vo) throws ParseException {
        Car entity = new Car();
        entity.setId(vo.getId());
        entity.setYear(vo.getYear());
        entity.setColor(vo.getColor());
        entity.setLicensePlate(vo.getLicensePlate());
        entity.setModel(vo.getModel());
        if(Objects.nonNull(vo.getUser())) {
            entity.setUser(UserMapper.toEntity(vo.getUser()));
        }

        return entity;
    }
    public static List<CarVO> toListVO(List<Car> entityList){
        return entityList.stream()
                .map(entity -> toVO(entity))
                .collect(Collectors.toList());
    }

    public static List<Car> toListEntity(List<CarVO> voList){
        return voList.stream()
                .map(entity -> {
                    try {
                        return toEntity(entity);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
