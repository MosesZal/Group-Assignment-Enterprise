package com.project.demo.Repositories;

import com.project.demo.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CarRepository extends JpaRepository<Car, Integer> {
  Car findCarByRegistrationNumber (String registrationNumber);
  Boolean existsCarByRegistrationNumber (String regNum);
}
