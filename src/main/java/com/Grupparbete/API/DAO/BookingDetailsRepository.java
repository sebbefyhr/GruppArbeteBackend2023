package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {
}
