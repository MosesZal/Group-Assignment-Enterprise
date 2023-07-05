package com.project.demo.Services;

import com.project.demo.Entities.Address;
import com.project.demo.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

  private AddressRepository addressRepository;

  @Autowired
  public AddressServiceImpl(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public Address saveAddress(Address address) {
    return addressRepository.save(address);
  }

  @Override
  public List<Address> getAllAddresses() {
    return addressRepository.findAll();
  }

  @Override
  public void deleteAddress(int ID) {
    addressRepository.deleteById(ID);
  }

  @Override
  public Address findByID (int ID) {
    Address address = null;
    Optional<Address> possibleAddress = addressRepository.findById(ID);
    if (possibleAddress.isPresent())
      address = possibleAddress.get();
    return address;
  }

  @Override
  public Boolean existsAddressByStreetAndPostalCodeAndCity(Address address) {
    return addressRepository.existsAddressByStreetAndPostalCodeAndCity(address.getStreet(), address.getPostalCode(),
            address.getCity());
  }

  @Override
  public Address findAddressByStreetAndPostalCodeAndCity(Address address) {
    return addressRepository.findAddressByStreetAndPostalCodeAndCity(address.getStreet(), address.getPostalCode(),
            address.getCity());
  }


}
