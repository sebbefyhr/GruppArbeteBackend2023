package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.CinemaBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaBookingRepository extends JpaRepository<CinemaBooking, Integer> {

}