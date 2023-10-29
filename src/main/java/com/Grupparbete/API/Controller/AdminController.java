package com.Grupparbete.API.Controller;

import com.Grupparbete.API.CurrencyConverter;
import com.Grupparbete.API.Entities.*;
import com.Grupparbete.API.Service.*;
import com.Grupparbete.API.exception.McBookingNotFoundException;
import com.Grupparbete.API.exception.McCustomerNotFoundException;
import com.Grupparbete.API.exception.MotorcycleNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private static final Logger logger = LogManager.getLogger("MyLogger");
//    @Autowired
//    private CustomerServiceImpl cinemaCustomerService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private CinemaRoomServiceImpl cinemaRoomService;

    @Autowired
    private AddressServiceImpl CinemaAddressService;

    private CustomerService customerService;
    private AddressService addressService;
    private DishesService dishesService;
    private SushiRoomService sushiRoomService;
    private SushiBookingService bookingService;
    private OrderService orderService;
    private McAdminServiceRepository mcAdminServiceRepository;


    @Autowired
    public AdminController(CustomerService custService, AddressService addService,
                           DishesService dishService, SushiRoomService rooService,
                           SushiBookingService bookService, OrderService ordService,
                           McAdminServiceRepository mcAdminServiceRepository){
        customerService = custService;
        addressService = addService;
        dishesService = dishService;
        sushiRoomService = rooService;
        bookingService = bookService;
        orderService = ordService;
        this.mcAdminServiceRepository = mcAdminServiceRepository;
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }

//    @GetMapping("/customers")
//    public List<Customer> getAllCustomers() {
//        return customerService.findAllCustomers();
//    }


//    @PostMapping("/customers")
//    public Customer saveCustomer(@RequestBody Customer s) {
//        logger.info("admin added customer " + s.getName());
//        s.setId(0);
//        Optional<Address> addressOptional = addressService.findAddressById(s.getAddress().getId());
//        if (addressOptional.isPresent()) {
//            Address address = addressOptional.get();
//            s.getAddress().setCity(address.getCity());
//            s.getAddress().setPostalCode(address.getPostalCode());
//            s.getAddress().setStreet(address.getStreet());
//        }
//        Customer customer = customerService.saveCustomer(s);
//        return customer;
//    }

    //    @PostMapping("/customers")
//    public Customer addCustomer(@RequestBody Customer newCustomer) {
//
//        Address existingAddress = addressService.findAddressByStreetAndPostalCodeAndCity(
//                newCustomer.getAddress().getStreet(),
//                newCustomer.getAddress().getPostalCode(),
//                newCustomer.getAddress().getCity()
//        );
//
//        if (existingAddress == null) {
//            addressService.saveAddress(newCustomer.getAddress());
//        }
//
//        Address customerAddress = addressService.findAddressByStreetAndPostalCodeAndCity(
//                newCustomer.getAddress().getStreet(),
//                newCustomer.getAddress().getPostalCode(),
//                newCustomer.getAddress().getCity()
//        );
//
//        newCustomer.setAddress(customerAddress);
//
//        Customer savedCustomer = customerService.saveCustomer(newCustomer);
//        int customerID = savedCustomer.getId();
//
//        logger.info("Admin added a new customer with ID: " + customerID);
//
//        return savedCustomer;
//    }

    //Tobbe
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }

//    @PutMapping("/customers/{id}")
//    public ResponseEntity<String> updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable int id) {
//        Customer existingCustomer = customerService.findCustomerById(id);
//        if (existingCustomer == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Kund med ID: " + id + " finns inte");
//        }
//
//        existingCustomer.setUsername(updatedCustomer.getUsername());
//        existingCustomer.setName(updatedCustomer.getName());
//
//        Address updatedAddress = updatedCustomer.getAddress();
//        Address existingAddress = existingCustomer.getAddress();
//
//        if (existingAddress != null && !existingAddress.equals(updatedAddress)) {
//            Address matchingAddress = addressService.findAddressByStreetAndPostalCodeAndCity(
//                    updatedAddress.getStreet(), updatedAddress.getPostalCode(), updatedAddress.getCity()
//            );
//
//            if (matchingAddress != null) {
//                existingCustomer.setAddress(matchingAddress);
//            } else {
//                existingAddress.setStreet(updatedAddress.getStreet());
//                existingAddress.setPostalCode(updatedAddress.getPostalCode());
//                existingAddress.setCity(updatedAddress.getCity());
//            }
//        } else if (existingAddress == null) {
//            Address newAddress = addressService.saveAddress(updatedAddress);
//            existingCustomer.setAddress(newAddress);
//        }
//
//        Customer updatedCustomerResult = customerService.saveCustomer(existingCustomer);
//
//        if (existingAddress != null) {
//            List<Customer> customersWithSameAddress = customerService.findCustomersByAddressId(existingAddress.getId());
//            if (customersWithSameAddress.size() == 0) {
//                addressService.deleteAddressById(existingAddress.getId());
//            }
//        }
//        logger.info("Admin updated customer with ID: " + id);
//        return ResponseEntity.ok("Kund med ID: " + id + " har uppdaterats.");
//    }

    // Tobbe
    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        if (customer.getCustomerId() > 0){
            customer.setCustomerId(0);
        }
        return customerService.saveCustomer(customer);
    }



//    @DeleteMapping("/customers/{id}")
//    public String deleteCustomer(@PathVariable int id) {
//        logger.info("admin deleted customer with ID " + id);
//        customerService.deleteCustomerById(id);
//        return "kund med id " + id + " har raderats";
//    }
//
//    @DeleteMapping("/customers/{id}")
//    public String SushideleteCustomer(@PathVariable int id) {
//        Customer customer = customerService.findCustomerById(id);
//
//        if (customer == null) {
//            return "Kund med ID " + id + " hittades inte.";
//        }
//
//        Address customerAddress = customer.getAddress();
//        int addressId = customerAddress.getId();
//
//        customerService.deleteCustomerById(id);
//
//        List<Customer> remainingCustomersWithSameAddress = customerService.findCustomersByAddressId(addressId);
//
//        if (remainingCustomersWithSameAddress.isEmpty()) {
//            addressService.deleteAddressById(addressId);
//        }
//
//        logger.info("Admin deleted customer with ID: " + id);
//
//        return "Kund med ID " + id + " har tagits bort.";
//    }

    //Tobbe
    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomerById(id);
        return ("Customer with id: " + id + " has been deleted!");
    }

//    @PutMapping("customers/{id}")
//    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer s) {
//        logger.info("admin updated customer with ID " + id);
//        s.setId(id);
//        Customer customer = customerService.saveCustomer(s);
//        return customer;
//    }


    @PostMapping("/movies")
    public Movie saveMovie(@RequestBody Movie movie) {
        logger.info("admin added movie " + movie.getTitle());
        movie.setId(0);
        return movieService.saveMovie(movie);
    }

    @DeleteMapping("/movies/{id}")
    public String deleteMovie(@PathVariable int id) {
        logger.info("admin deleted movie with ID " + id);
        movieService.deleteMovieById(id);
        return "film med id " + id + " har raderats";
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }

    @PutMapping("rooms/{id}")
    public CinemaRoom updateRoom(@PathVariable int id, @RequestBody CinemaRoom s) {
        logger.info("admin updated room with ID " + id);
        s.setId(id);
        CinemaRoom updatedRoom = cinemaRoomService.saveRoom(s);
        return updatedRoom;
    }




    @PostMapping("/sushis")
    public Dishes addDish(@RequestBody Dishes dish) {
        Dishes addedDish = dishesService.addDish(dish);
        logger.info("Admin added a new dish with ID: " + addedDish.getId());

        return dishesService.addDish(dish);
    }

    @DeleteMapping("/sushis/{id}")
    public String deleteDish(@PathVariable int id) {
        Dishes deletedDish = dishesService.findDishById(id);

        if (deletedDish == null) {
            return "Maträtt med ID " + id + " hittades inte.";
        }

        List<OrderDetails> orderDetailsList = orderService.findOrdersContainingDish(deletedDish);

        for (OrderDetails orderDetails : orderDetailsList) {
            int quantity = orderDetails.getQuantity();
            double priceSEK = orderDetails.getPriceSEK();

            CurrencyConverter converter = new CurrencyConverter();
            Order order = orderDetails.getOrder();
            order.setQuantity(order.getQuantity() - quantity);
            order.setTotalPriceSEK(order.getTotalPriceSEK() - priceSEK);
            try {
                order.setTotalPriceYEN((int) CurrencyConverter.SekToRequestedCurrency(order.getTotalPriceSEK(), "JPY"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<BookingDetails> bookingDetailsList = bookingService.findBookingDetailsContainingDish(deletedDish);

        for (BookingDetails bookingDetails : bookingDetailsList) {
            int quantity = bookingDetails.getQuantity();
            double priceSEK = bookingDetails.getPriceSEK();

            CurrencyConverter converter = new CurrencyConverter();
            SushiBooking booking = bookingDetails.getBooking();
            booking.setTotalPriceSEK(booking.getTotalPriceSEK() - priceSEK);
            try {
                booking.setTotalPriceYEN((int) CurrencyConverter.SekToRequestedCurrency(booking.getTotalPriceSEK(), "JPY"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        dishesService.deleteDish(id);
        logger.info("Admin deleted Dish with ID: " + id);
        return "Maträtt med ID " + id + " har tagits bort.";
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<String> updateRoom(@RequestBody SushiRoom updatedRoom, @PathVariable int id) {
        SushiBooking existingRoom = SushiRoomService.findRoomById(id);
        if (existingRoom == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rum med ID: " + id + " finns inte");
        }

        existingRoom.setName(updatedRoom.getName());
        existingRoom.setDescription(updatedRoom.getDescription());
        existingRoom.setMaxGuests(updatedRoom.getMaxGuests());

        SushiRoom updated = sushiRoomService.updateRoom(existingRoom, id);

        if (updated != null) {
            logger.info("Admin updated room with ID: " + id);
            return ResponseEntity.ok("Rum med ID " + id + " har uppdaterats.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Misslyckades med att uppdatera rummet.");
        }
    }

    @GetMapping("/sushis")
    public List<Dishes> findAllDishes() {
        List<Dishes> dishes = dishesService.findAllDishes();
        return dishes;
    }

    //sebbe
    @GetMapping("/allBikes")
    public List<Motorcycle> getAllBikes(){
        return mcAdminServiceRepository.getAllBikes();
    }

    //sebbe
    @PutMapping("/bike/{id}")
    public Motorcycle updateMC(@RequestBody Motorcycle mc, @PathVariable int id){

        Optional<Motorcycle> bike = mcAdminServiceRepository.findBikeById(id);

        if(!bike.isPresent()){
            logger.error("Mc med id: " + id + " finns inte i databasen.");
            throw new MotorcycleNotFoundException("Bike with ID is not found");
        }

        mc.setId(id);
        logger.info("Admin uppdaterade mc: " + mc.getId() + " " + mc.getBrand() +" " + mc.getModel());
        return mcAdminServiceRepository.save(mc);
    }

    //sebbe
    @DeleteMapping("/bookings/{id}")
    public String deleteBooking(@PathVariable int id){
        Optional<McBooking> booking  = mcAdminServiceRepository.findBookingById(id);

        if(!booking.isPresent()){
            logger.error("Ingen bokning med id: " + id + " hittades i vårat system");
            throw new McBookingNotFoundException("No Booking with this reference was found, please check again.");
        }

        McBooking b = mcAdminServiceRepository.findById(id);
        Motorcycle mc = b.getMc();
        mc.setRented(false);
        mcAdminServiceRepository.deleteBookingById(id);

        logger.info("Admin tog bort bokning med nummer: " + b.getId());
        return "Bokningen har tagits bort.";
    }

    //sebbe
    @PostMapping("/bike")
    public Motorcycle addMC(@RequestBody Motorcycle mc){

        mc.setId(0);
        logger.info("Admin la till en ny MC: " + mc.getBrand() + " " + mc.getModel());
        return mcAdminServiceRepository.save(mc);
    }

    //sebbe
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        Optional<Customer> c = mcAdminServiceRepository.findCustomerById(id);
        if(!c.isPresent()){
            logger.error("Kund med id: " + id + " finns inte i databasen.");
            throw new McCustomerNotFoundException("We cant find a customer with this id in our database");
        }
        customer.setCustomerId(id);
        logger.info("Admin uppdaterade kund: " + customer.getCustomerId() +", "  + customer.getFirstName() + " " + customer.getLastName());
        return mcAdminServiceRepository.save(customer);
    }

    //sebbe
    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id){
        Optional<Customer> customer = mcAdminServiceRepository.findCustomerById(id);
        if(customer.isEmpty()){
            logger.error("Kund med id: " + id + " finns inte i databasen.");
            throw new McCustomerNotFoundException("Customer with the id is not found in the database");
        }
        logger.info("Admin tog bort kund med id: " + id);
        return mcAdminServiceRepository.deleteCustomerById(id);
    }
    //sebbe
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){

        customer.setCustomerId(0);
        logger.info("Admin la till ny kund: " + customer.toString());
        return mcAdminServiceRepository.save(customer);
    }
    //sebbe
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return mcAdminServiceRepository.getAllCustomers();
    }

}