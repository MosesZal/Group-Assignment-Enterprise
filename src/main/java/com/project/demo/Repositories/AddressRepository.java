package com.project.demo.Repositories;

import com.project.demo.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Integer> {
  Boolean existsAddressByStreetAndPostalCodeAndCity (String street, String postalCode, String city);
  Address findAddressByStreetAndPostalCodeAndCity (String street, String postalCode, String city);
}
