package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByAddress_Id(int addressId);
}