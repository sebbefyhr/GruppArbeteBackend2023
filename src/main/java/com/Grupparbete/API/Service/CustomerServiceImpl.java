package com.Grupparbete.API.Service;

import com.Grupparbete.API.DAO.CustomerRepository;
import com.Grupparbete.API.Entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        System.out.println("Saved customer");
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        System.out.println("Found all customers");
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomerById(int customerId) {
        System.out.println("Removed customer from database");
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(Customer customers, int id) {
        Customer c = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Kunde inte finna medlem "+ customers +" med Id: "+id));
        c.setUsername(customers.getUsername());
        c.setName(customers.getName());
        c.setAddress(customers.getAddress());
        customerRepository.save(c);
        return c;
    }

    @Override
    public List<Customer> findCustomersByAddressId(int addressId) {
        return customerRepository.findByAddress_Id(addressId);
    }
    @Override
    public Customer findCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        Customer customers = null;
        if (customer.isPresent()){
            customers = customer.get();
        }
        else{
            throw new RuntimeException("Kunde inte finna nån person med id: " + id);
        }
        return customers;
    }

}