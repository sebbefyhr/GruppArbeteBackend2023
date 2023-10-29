package com.Grupparbete.API.Service;

import com.Grupparbete.API.DAO.McBookingRepository;
import com.Grupparbete.API.DAO.McCustomerRepository;
import com.Grupparbete.API.DAO.MotorcycleRepository;
import com.Grupparbete.API.Entities.McBooking;
import com.Grupparbete.API.Entities.Motorcycle;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class McCustomerServiceImpl implements McCustomerServiceRepository {

    private MotorcycleRepository motorcycleRepository;
    private McBookingRepository bookingRepository;
    private McCustomerRepository customerRepository;

    @Autowired
    public McCustomerServiceImpl (MotorcycleRepository mcr, McBookingRepository bookingRepository, McCustomerRepository customerRepository){
        this.motorcycleRepository = mcr;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }
    @Override
    public List<Motorcycle> findAllAvailableMcs() {
        return motorcycleRepository.findMotorcyclesByRentedIsFalse();

    }

    @Override
    public List<McBooking> findAllBookingsByBikeId(int id, LocalDate tomorrow) {
        List<McBooking> list = bookingRepository.findBookingsByMc_IdAndStartDateIsBefore(id, tomorrow);
        return list;
    }

    @Override
    public McBooking save(McBooking mcBooking){
        return bookingRepository.save(mcBooking);
    }

    @Override
    public Optional<McBooking> findBookingById(int id){
        return bookingRepository.findById(id);
    }

    @Override
    public List<McBooking> findAllBookings(){
        return bookingRepository.findAll();
    }


    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Motorcycle findMotorcycleById(int id) {
        return motorcycleRepository.findMotorcycleById(id);
    }

    @Override
    public void saveMc(Motorcycle mc) {
        motorcycleRepository.save(mc);
    }
}
