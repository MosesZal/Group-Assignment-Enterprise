package com.project.demo.Services;

import com.project.demo.Entities.Car;
import com.project.demo.Repositories.CarRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

  private static final Logger logger = Logger.getLogger(CarServiceImpl.class);
  private CarRepository carRepository;

  @Autowired
  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @Override
  public ResponseEntity <String> carAddingLogic(Car newCar, BindingResult bindingResult) {
    newCar.setCarID(0);
    newCar.setBooked(false);
    if (carRepository.existsCarByRegistrationNumber(newCar.getRegistrationNumber())) {
      logger.error("An attempt to create a new car has failed!");
      return new ResponseEntity<>("Process failed! There is already an existing car with the same registration number!",
              HttpStatus.CONFLICT);
    }
    else {
      carRepository.save(newCar);
      logger.info("Car created successfully with registration number: " + newCar.getRegistrationNumber() + "!");
      return new ResponseEntity<>("Car created successfully!", HttpStatus.CREATED);
    }
  }

  @Override
  public ResponseEntity <String> carUpdatingLogic (Car carBeingEdited, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      logger.error("An attempt to update a car has failed!");
      return ResponseEntity.badRequest().body(carPutErrors(bindingResult));
    }
    else if (carRepository.findCarByRegistrationNumber(carBeingEdited.getRegistrationNumber()) == null) {
      logger.error("An attempt to update a car has failed!");
      return new ResponseEntity<>("Process Failed! There isn't a car with a registration number " +
              carBeingEdited.getRegistrationNumber() + "!", HttpStatus.NOT_FOUND);
    }
    else {
      /* updateCar method isn't throwing a ConstraintViolationException, which is handled by the ExceptionHandler, when
      there are missing fields. So the above if statement is needed if you wanna display the missing fields in a message
      for the user. */
      updateCar(carBeingEdited);
      logger.info("The car with the registration number " + carBeingEdited.getRegistrationNumber() + " is successfully" +
              "updated!");
      return new ResponseEntity<>("Car updated successfully!", HttpStatus.OK);
    }
  }

  @Override
  public Car updateCar(Car carIn) {
    Car carOut = carRepository.findCarByRegistrationNumber(carIn.getRegistrationNumber());
    if (carOut != null) {
      carOut.setRentalPricePerDay(carIn.getRentalPricePerDay());
      carOut.setMake(carIn.getMake());
      carOut.setModel(carIn.getModel());
      return carRepository.save(carOut);
    }
    else
      return null;
  }

  @Override
  public List<Car> getAllCars() {
    return carRepository.findAll();
  }

  @Override
  public Car cancelCar(Car car) {

    car.setBooked(false);

    return carRepository.save(car);

  }

  @Override
  public Car findByID(int ID) {
    Car car = null;
    Optional<Car> possibleCar = carRepository.findById(ID);
    if (possibleCar.isPresent())
      car = possibleCar.get();
    return car;
  }

  @Override
  public void deleteCar(int ID) {
    carRepository.deleteById(ID);
  }

  @Override
  public String carPutErrors(BindingResult bindingResult) {
    StringBuilder errors = new StringBuilder("Process failed due to the following error(s):\n");
    for (FieldError fieldError : bindingResult.getFieldErrors())
      errors.append("* ").append(fieldError.getDefaultMessage()).append("\n");
    return errors.toString();
  }

  @Override
  public List<Car> listAvailableCars(){
    List<Car> allCars = getAllCars();
    List<Car> availableCars = new ArrayList<>();
    for(Car car : allCars){
      if(!car.isBooked())
        availableCars.add(car);
    }
    return availableCars;
  }

}
