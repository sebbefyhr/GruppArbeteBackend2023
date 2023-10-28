package com.Grupparbete.API.Service;

import com.Grupparbete.API.DAO.CinemaBookingRepository;
import com.Grupparbete.API.Entities.CinemaBooking;
import com.Grupparbete.API.Entities.CinemaRoom;
import com.Grupparbete.API.Entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CinemaBookingServiceImpl implements CinemaBookingService{

    @Autowired
    private CinemaBookingRepository CinemabookingRepository;


    @Override
    public int calculateTotalPrice(CinemaBooking booking) {
        int numberOfGuests = booking.getGuests();
        Movie selectedMovie = booking.getMovie();
        CinemaRoom selectedRoom = booking.getRoom();
        int moviePrice = selectedMovie.getPrice();
        int roomPrice = selectedRoom.getPrice();
        int totalprice = (moviePrice * numberOfGuests) + roomPrice;

        return totalprice;
    }


    public CinemaBooking saveBooking(CinemaBooking booking) {
        System.out.println("Customer saved a booking");
        return CinemabookingRepository.save(booking);
    }

    @Override
    public Optional<CinemaBooking> findById(int id) {
        return CinemabookingRepository.findById(id);
    }

}
