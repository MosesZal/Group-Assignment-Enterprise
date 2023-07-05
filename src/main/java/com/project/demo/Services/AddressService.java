package com.project.demo.Services;

import com.project.demo.Entities.Address;

import java.util.List;

public interface AddressService {
  Address saveAddress(Address address);
  List<Address> getAllAddresses();
  void deleteAddress(int ID);
  Address findByID (int ID);
  Boolean existsAddressByStreetAndPostalCodeAndCity (Address address);
  Address findAddressByStreetAndPostalCodeAndCity (Address address);
}
