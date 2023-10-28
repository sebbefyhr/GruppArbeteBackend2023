package com.Grupparbete.API.Controller;

import com.Grupparbete.API.Entities.*;
import com.Grupparbete.API.Service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private static final Logger logger = LogManager.getLogger("MyLogger");
    @Autowired
    private CustomerServiceImpl cinemaCustomerService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private CinemaRoomServiceImpl cinemaRoomService;

    @Autowired
    private AddressServiceImpl CinemaAddressService;

    private CustomerService sushiCustomerService;
    private AddressService addressService;
    private DishesService dishesService;
    private SushiRoomService addressRoomService;
    private SushiBookingService bookingService;
    private OrderService orderService;


    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer s) {
        logger.info("admin added customer " + s.getName());
        s.setId(0);
        Optional<Address> addressOptional = addressService.findById(s.getAddress().getId());
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            s.getAddress().setCity(address.getCity());
            s.getAddress().setPostalcode(address.getPostalcode());
            s.getAddress().setStreet(address.getStreet());
        }
        Customer customer = cinemaCustomerService.saveCustomer(s);
        return customer;
    }


    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return cinemaCustomerService.findAllCustomers();
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        logger.info("admin deleted customer with ID " + id);
        cinemaCustomerService.deleteCustomerById(id);
        return "kund med id " + id + " har raderats";
    }

    @PutMapping("customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer s) {
        logger.info("admin updated customer with ID " + id);
        s.setId(id);
        Customer customer = cinemaCustomerService.saveCustomer(s);
        return customer;
    }

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


    public AdminController(CustomerService custService, AddressService addService, DishesService dishService, SushiRoomService rooService, SushiBookingService bookService, OrderService ordService){
        sushiCustomerService = custService;
        addressService = addService;
        dishesService = dishService;
        addressRoomService = rooService;
        bookingService = bookService;
        orderService = ordService;
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return sushiCustomerService.findAllCustomers();
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer newCustomer) {

        Address existingAddress = addressService.findAddressByStreetAndPostalCodeAndCity(
                newCustomer.getAddress().getStreet(),
                newCustomer.getAddress().getPostalcode(),
                newCustomer.getAddress().getCity()
        );

        if (existingAddress == null) {
            addressService.saveAddress(newCustomer.getAddress());
        }

        Address customerAddress = addressService.findAddressByStreetAndPostalCodeAndCity(
                newCustomer.getAddress().getStreet(),
                newCustomer.getAddress().getPostalcode(),
                newCustomer.getAddress().getCity()
        );

        newCustomer.setAddress(customerAddress);

        Customer savedCustomer = sushiCustomerService.saveCustomer(newCustomer);
        int customerID = savedCustomer.getId();

        logger.info("Admin added a new customer with ID: " + customerID);

        return savedCustomer;
    }


    @DeleteMapping("/customers/{id}")
    public String SushideleteCustomer(@PathVariable int id) {
        Customer customer = sushiCustomerService.findCustomerById(id);

        if (customer == null) {
            return "Kund med ID " + id + " hittades inte.";
        }

        Address customerAddress = customer.getAddress();
        int addressId = customerAddress.getId();

        sushiCustomerService.deleteCustomerById(id);

        List<Customer> remainingCustomersWithSameAddress = sushiCustomerService.findCustomersByAddressId(addressId);

        if (remainingCustomersWithSameAddress.isEmpty()) {
            addressService.deleteAddress(addressId);
        }

        logger.info("Admin deleted customer with ID: " + id);

        return "Kund med ID " + id + " har tagits bort.";
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable int id) {
        Customer existingCustomer = sushiCustomerService.findCustomerById(id);
        if (existingCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Kund med ID: " + id + " finns inte");
        }

        existingCustomer.setUsername(updatedCustomer.getUsername());
        existingCustomer.setName(updatedCustomer.getName());

        Address updatedAddress = updatedCustomer.getAddress();
        Address existingAddress = existingCustomer.getAddress();

        if (existingAddress != null && !existingAddress.equals(updatedAddress)) {
            Address matchingAddress = addressService.findAddressByStreetAndPostalCodeAndCity(
                    updatedAddress.getStreet(), updatedAddress.getPostalcode(), updatedAddress.getCity()
            );

            if (matchingAddress != null) {
                existingCustomer.setAddress(matchingAddress);
            } else {
                existingAddress.setStreet(updatedAddress.getStreet());
                existingAddress.setPostalcode(updatedAddress.getPostalcode());
                existingAddress.setCity(updatedAddress.getCity());
            }
        } else if (existingAddress == null) {
            Address newAddress = addressService.saveAddress(updatedAddress);
            existingCustomer.setAddress(newAddress);
        }

        Customer updatedCustomerResult = sushiCustomerService.saveCustomer(existingCustomer);

        if (existingAddress != null) {
            List<Customer> customersWithSameAddress = sushiCustomerService.findCustomersByAddressId(existingAddress.getId());
            if (customersWithSameAddress.size() == 0) {
                addressService.deleteAddress(existingAddress.getId());
            }
        }
        logger.info("Admin updated customer with ID: " + id);
        return ResponseEntity.ok("Kund med ID: " + id + " har uppdaterats.");
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
            order.setTotalPriceYEN((int)(order.getTotalPriceSEK() * converter.getSEKToYenExchangeRate()));
        }

        List<BookingDetails> bookingDetailsList = bookingService.findBookingDetailsContainingDish(deletedDish);

        for (BookingDetails bookingDetails : bookingDetailsList) {
            int quantity = bookingDetails.getQuantity();
            double priceSEK = bookingDetails.getPriceSEK();

            CurrencyConverter converter = new CurrencyConverter();
            SushiBooking booking = bookingDetails.getBooking();
            booking.setTotalPriceSEK(booking.getTotalPriceSEK() - priceSEK);
            booking.setTotalPriceYEN((int)(booking.getTotalPriceSEK() * converter.getSEKToYenExchangeRate()));
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

        SushiRoom updated = addressRoomService.updateRoom(existingRoom, id);

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
}