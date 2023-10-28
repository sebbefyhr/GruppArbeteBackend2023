package com.Grupparbete.API.Service;

import com.Grupparbete.API.DAO.AddressRepository;
import com.Grupparbete.API.Entities.Address;
import com.Grupparbete.API.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Optional<Address> findById(int id) {
        return addressRepository.findById(id);
    }
           @Autowired
           public AddressServiceImpl(AddressRepository addressRepository) {
            this.addressRepository = addressRepository;
        }

        @Override
        public Address findAddressById(int id) {
            return addressRepository.findById(id).orElse(null);
        }

        @Override
        public Address saveAddress(Address address) {
            return addressRepository.save(address);
        }

        @Override
        public Address findAddressByStreetAndPostalCodeAndCity(String street, String postalCode, String city) {
            return addressRepository.findAddressByStreetAndPostalCodeAndCity(street, postalCode, city);
        }

        @Override
        public void deleteAddress(int id) {
            addressRepository.deleteById(id);
        }
    }

