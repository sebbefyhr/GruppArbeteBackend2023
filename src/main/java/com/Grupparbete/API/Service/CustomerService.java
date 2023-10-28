package com.Grupparbete.API.Service;

import com.Grupparbete.API.Entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> findAllCustomers();
    void deleteCustomerById(int customerId);
    Customer findCustomerById(int id);
    Customer updateCustomer(Customer customers, int id);
    List<Customer> findCustomersByAddressId(int addressId);

}


