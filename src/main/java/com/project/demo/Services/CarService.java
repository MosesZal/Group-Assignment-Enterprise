package com.project.demo.Services;

import com.project.demo.Entities.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CarService {
  ResponseEntity <String> carAddingLogic(Car car, BindingResult bindingResult);
  ResponseEntity <String> carUpdatingLogic (Car carBeingEdited, BindingResult bindingResult);
  List<Car> getAllCars();
  List<Car> listAvailableCars();
  Car cancelCar(Car car);
  Car findByID (int ID);
  void deleteCar (int ID);
  Car updateCar(Car car);
  String carPutErrors(BindingResult bindingResult);

}
