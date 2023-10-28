package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findAddressByStreetAndPostalCodeAndCity(String street, String postalCode, String city);
}