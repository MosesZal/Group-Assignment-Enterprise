package com.project.demo.Entities;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int addressID;

  @NotBlank(message = "\"Street\" can't be blank!")
  @Column(length = 50, nullable = false)
  private String street;

  @NotBlank(message = "\"Postal code\" can't be blank!")
  @Column(length = 20, nullable = false)
  private String postalCode;

  @NotBlank(message = "\"City\" can't be blank!")
  @Column(length = 50, nullable = false)
  private String city;

  public Address() {
  }

  public Address(int addressID, String street, String postalCode, String city) {
    this.addressID = addressID;
    this.street = street;
    this.postalCode = postalCode;
    this.city = city;
  }

  public int getAddressID() {
    return addressID;
  }

  public void setAddressID(int addressID) {
    this.addressID = addressID;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
