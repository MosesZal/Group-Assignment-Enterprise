package com.project.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int customerID;

  @NotBlank(message = "\"User name\" can't be blank!")
  @Column(length = 80, nullable = false, unique = true)
  private String userName;

  @NotBlank(message = "\"Full name\" can't be blank!")
  @Column(length = 80, nullable = false)
  private String fullName;

  @NotBlank(message = "\"Email\" can't be blank!")
  @Column(length = 200, nullable = false, unique = true)
  private String email;

  @NotBlank(message = "\"Phone number\" can't be blank!")
  @Column(length = 20, nullable = false, unique = true)
  private String phoneNumber;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
  @JoinColumn(name = "addressID")
  @Valid
  private Address address;


  @OneToMany (mappedBy = "customer" ,cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
  @JsonIgnore
  private List <Booking> bookings;

  public Customer() {
  }

  public Customer(int customerID, String userName, String fullName, String email, String phoneNumber, Address address, List<Booking> bookings) {
    this.customerID = customerID;
    this.userName = userName;
    this.fullName = fullName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.bookings = bookings;
  }

  public int getCustomerID() {
    return customerID;
  }

  public void setCustomerID(int employeeID) {
    this.customerID = employeeID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public void setBookings(List<Booking> bookings) {
    this.bookings = bookings;
  }


}
