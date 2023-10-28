package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
