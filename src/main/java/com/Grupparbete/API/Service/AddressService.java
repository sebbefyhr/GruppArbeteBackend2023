package com.Grupparbete.API.Service;

import com.Grupparbete.API.Entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAll();
    Address findAddressById(int id);
    Address updateAddress(int id, Address address);
    Address saveAddress(Address address);
    void deleteAddressById(int id);
    Address findAddressByStreetAndPostalCodeAndCity(String street, int postalCode, String city);
    Address checkIfExistsInDatabaseIfNotSave(Address address, boolean autoSave);
}
