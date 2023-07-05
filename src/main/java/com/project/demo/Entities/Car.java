package com.project.demo.Entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int carID;

  @Min(value = 1, message = "\"Rental price per day\" can't be blank or 0!")
  @Column(length = 9, nullable = false)
  private int rentalPricePerDay;

  @NotBlank(message = "\"Make\" can't be blank!")
  @Column(length = 25, nullable = false)
  private String make;

  @NotBlank(message = "\"Model\" can't be blank!")
  @Column(length = 50, nullable = false)
  private String model;

  @NotBlank(message = "\"Registration number\" can't be blank!")
  @Column(length = 30, nullable = false, unique = true)
  private String registrationNumber;

  @Column(nullable = false)
  private boolean booked;

  public Car() {
  }

  public Car(int carID, int rentalPricePerDay, String make, String model, String registrationNumber, boolean booked) {
    this.carID = carID;
    this.rentalPricePerDay = rentalPricePerDay;
    this.make = make;
    this.model = model;
    this.registrationNumber = registrationNumber;
    this.booked = booked;
  }



  public int getCarID() {
    return carID;
  }

  public void setCarID(int carID) {
    this.carID = carID;
  }

  public int getRentalPricePerDay() {
    return rentalPricePerDay;
  }

  public void setRentalPricePerDay(int rentalPricePerDay) {
    this.rentalPricePerDay = rentalPricePerDay;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public boolean isBooked() {
    return booked;
  }

  public void setBooked(boolean booked) {
    this.booked = booked;
  }
}
