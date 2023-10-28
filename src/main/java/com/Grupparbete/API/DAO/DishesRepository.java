package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepository extends JpaRepository<Dishes, Integer> {
}
