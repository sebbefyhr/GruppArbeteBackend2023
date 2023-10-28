package com.Grupparbete.API.Service;

import com.Grupparbete.API.Entities.Address;

import java.util.Optional;

public interface AddressService {

    Optional<Address> findById(int id);

    Address findAddressById(int id);
    Address saveAddress(Address address);
    Address findAddressByStreetAndPostalCodeAndCity(String street, String postalCode, String city);
    void deleteAddress(int id);
}
