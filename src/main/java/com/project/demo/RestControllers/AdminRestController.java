package com.project.demo.RestControllers;

import com.project.demo.Entities.Address;
import com.project.demo.Entities.Booking;
import com.project.demo.Entities.Car;
import com.project.demo.Entities.Customer;
import com.project.demo.Services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminRestController {

  private static final Logger logger = Logger.getLogger(AdminRestController.class);

  private AddressService addressService;
  private CustomerService customerService;
  private CarService carService;
  private BookingService bookingService;

  @Autowired
  public AdminRestController(AddressService addressService, CustomerServiceImpl customerService, CarService carService, BookingService bookingService) {
    this.addressService = addressService;
    this.customerService = customerService;
    this.carService = carService;
    this.bookingService = bookingService;
  }

  @GetMapping("/customers")
  public List <Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/allcars")
  public List <Car> getAllCars() {
    return carService.getAllCars();
  }

  @GetMapping("/orders")
  public List <Booking> getAllBookings() {
    return bookingService.getAllBookings();
  }

  /** Bonus endpoint for testing and presentation purposes (Moussa) **/
  @GetMapping("/addresses")
  public List <Address> getAllAddresses() {
    return addressService.getAllAddresses();
  }

  @PostMapping("/addcustomer")
  public ResponseEntity<String> addCustomer (@Valid @RequestBody Customer newCustomer, BindingResult bindingResult) {
    return customerService.customerAddingLogic(newCustomer, bindingResult);
  }

  @PostMapping("/addcar")
  public ResponseEntity<String> addCar (@Valid @RequestBody Car newCar, BindingResult bindingResult) {
    return carService.carAddingLogic(newCar, bindingResult);
  }

  @PutMapping("/updatecar")
  public ResponseEntity <String> updateCar (@Valid @RequestBody Car carBeingEdited, BindingResult bindingResult) {
    return carService.carUpdatingLogic(carBeingEdited, bindingResult);
  }

  @PutMapping("/updatecustomer")
  public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
    return customerService.customerUpdatingLogic(customer);
  }

  @DeleteMapping("/deletecustomer")
  public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer){
    return customerService.customerDeleteLogic(customer);
  }

  @DeleteMapping("/deletecar")
  public ResponseEntity<String> deleteCar(@RequestBody Car car){
    int id = car.getCarID();
    carService.deleteCar(id);
    logger.info("Car with registration number: " + car.getRegistrationNumber() + " was deleted by admin");
    return new ResponseEntity<String>("Car deleted.", HttpStatus.OK);
  }

  @DeleteMapping("/deleteorder")
  public ResponseEntity<String> deleteBooking(@RequestBody Booking booking){

    logger.info("A booking is deleted by admin. Customer: " + booking.getCustomer().getFullName() + "Car: " + booking.getCar().getModel() + " Date: " + booking.getBookingDate());

    bookingService.deleteBooking(booking.getBookingID());

    return new ResponseEntity<>("Booking is deleted", HttpStatus.OK);
  }
}
