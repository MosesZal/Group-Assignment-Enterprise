package com.project.demo.RestControllers;

import com.project.demo.Entities.Booking;
import com.project.demo.Entities.Car;
import com.project.demo.Entities.Customer;
import com.project.demo.Services.BookingService;
import com.project.demo.Services.CarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

    private static final Logger logger = Logger.getLogger(CustomerRestController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;


    @GetMapping("/cars")
    public List<Car> getAvailableCars() {
        return carService.listAvailableCars();
    }

    @PostMapping("/ordercar")

    public Booking makeABooking(@RequestBody Booking booking) {

        bookingService.saveBooking(booking);

        logger.info("A booking is made on car: " + booking.getCar().getModel() + " on date: " + booking.getBookingDate());
        return booking;
    }

    @PutMapping("/cancelorder")

    public ResponseEntity<Booking> cancelBooking(@RequestBody Booking booking) {

        carService.cancelCar(booking.getCar());

        logger.info(booking.getCustomer().getFullName() + " cancelled a booking on car: " + booking.getCar().getModel() + " on date: " + booking.getBookingDate());

        return ResponseEntity.ok(bookingService.cancelBooking(booking));

    }

    @GetMapping("/myorders")

    public List<Booking> getAllMyBookings(@RequestBody Customer customer) {

        List<Booking> allBookings = bookingService.getAllBookings();

        List<Booking> myBookings = new ArrayList<>();
        for (Booking booking : allBookings) {
            if (booking.getCustomer().getCustomerID() == customer.getCustomerID())
                myBookings.add(booking);
        }
        return myBookings;
    }

}


