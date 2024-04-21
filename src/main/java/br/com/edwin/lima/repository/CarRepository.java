package br.com.edwin.lima.repository;

import br.com.edwin.lima.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.user.id=:userId")
    public List<Car> findAllCarsByUserId(Long userId);
}
