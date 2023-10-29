package com.Grupparbete.API.DAO;
import com.Grupparbete.API.Entities.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

    List<Motorcycle> findMotorcyclesByRentedIsFalse();
    Motorcycle save(Motorcycle mc);
    Motorcycle findMotorcycleById(int id);
}
