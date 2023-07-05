package com.project.demo.Entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int bookingID;

  @Column(nullable = false)
  @DateTimeFormat (pattern = "yyyy-MM-dd")
  private Date bookingDate;

  @Column
  private Boolean isActive;

  @JoinColumn(name = "carID")
  @OneToOne
  private Car car;


  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
  @JoinColumn(name = "customerID")
  private Customer customer;

  public Booking() {
  }

  public Booking(int bookingID, Date bookingDate, Customer customer) {
    this.bookingID = bookingID;
    this.bookingDate = bookingDate;
    this.customer = customer;
  }

  public Booking(int bookingID, Date bookingDate, Boolean isActive, Car car, Customer customer) {
    this.bookingID = bookingID;
    this.bookingDate = bookingDate;
    this.isActive = isActive;
    this.car = car;
    this.customer = customer;
  }

  public Booking(Date bookingDate, Boolean isActive, Car car, Customer customer) {
    this.bookingDate = bookingDate;
    this.isActive = isActive;
    this.car = car;
    this.customer = customer;
  }


  public int getBookingID() {
    return bookingID;
  }

  public void setBookingID(int bookingID) {
    this.bookingID = bookingID;
  }

  public Date getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(Date bookingDate) {
    this.bookingDate = bookingDate;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
