package com.project.demo.Services;

import com.project.demo.Entities.Address;
import com.project.demo.Entities.Customer;
import com.project.demo.Exceptions.ResourceNotFoundException;
import com.project.demo.Repositories.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);

  private CustomerRepository customerRepository;
  private AddressService addressService;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository, AddressService addressService) {
    this.customerRepository = customerRepository;
    this.addressService = addressService;
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer updateCustomer(Customer customer) {
    int id = customer.getCustomerID();
    Customer c = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
    c.setUserName(customer.getUserName());
    c.setFullName(customer.getFullName());
    c.setEmail(customer.getEmail());
    c.setPhoneNumber(customer.getPhoneNumber());
    c.setAddress(customer.getAddress());
    customerRepository.save(c);
    return c;
  }

  @Override
  public ResponseEntity<String> customerUpdatingLogic(Customer customer) {
    Address address = addressService.findByID(customer.getAddress().getAddressID());
    if (address != null) {
      customer.setAddress(address);
    }
    logger.info("Admin updated customer with user name: " + customer.getUserName() + " !");
    updateCustomer(customer);
    return new ResponseEntity<>("Customer updated successfully!", HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> customerDeleteLogic(Customer customer) {
    int id = customer.getCustomerID();
    deleteCustomer(id);
    logger.info("Admin deleted customer with user name: " + customer.getUserName() + " successfully!");
    return new ResponseEntity<String>("Member deleted.", HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> customerAddingLogic(Customer newCustomer, BindingResult bindingResult) {
    newCustomer.setCustomerID(0);
    Address address = addressService.findByID(newCustomer.getAddress().getAddressID());
    /*The below condition saves and returns because when using the address ID to assign the customer to an already
      existing address, the next statement will still see that the bindingResult has errors. Exceptions thrown here are
      still handled however in the ExceptionHandler */
    if (address != null) {
      newCustomer.setAddress(address);
      customerRepository.save(newCustomer);
      logger.info("Customer created successfully with user name: " + newCustomer.getUserName() + "!");
      return new ResponseEntity<>( "Address found!\n" +
              "Customer created and assigned successfully to the specified address!", HttpStatus.CREATED);
    }
    /*Even though the below condition is handled in the ExceptionHandler, I still wanted to customize the message for the
      user in a way that doesn't involve hardcoding */
    else if (bindingResult.hasErrors()) {
      logger.error("An attempt to create a new customer has failed!");
      return ResponseEntity.badRequest().body(customerPostErrors(bindingResult));
    }
    else if (existsCustomerByUserNameOrEmailOrPhoneNumber(newCustomer)) {
      logger.error("An attempt to create a new customer has failed!");
      return new ResponseEntity<>("Process failed! There is already an existing customer with the same \"user name\", "
              + "\"email\", and/or \"phone number\"!", HttpStatus.CONFLICT);
    }
    else {
      if (addressService.existsAddressByStreetAndPostalCodeAndCity(newCustomer.getAddress())) {
        newCustomer.setAddress(addressService.findAddressByStreetAndPostalCodeAndCity(newCustomer.getAddress()));
        customerRepository.save(newCustomer);
        logger.info("Customer created successfully with user name: " + newCustomer.getUserName() + "!");
        return new ResponseEntity<>("Address already exists!\n" +
                "Customer created and assigned successfully to that address!", HttpStatus.CREATED);
      }
      else {
        customerRepository.save(newCustomer);
        logger.info("Address created successfully!");
        logger.info("Customer created successfully with user name: " + newCustomer.getUserName() + "!");
        return new ResponseEntity<>("Customer and address created successfully!", HttpStatus.CREATED);
      }
    }
  }

  @Override
  public Customer findByID(int ID) {
    Customer customer = null;
    Optional<Customer> possibleCustomer = customerRepository.findById(ID);
    if (possibleCustomer.isPresent())
      customer = possibleCustomer.get();
    return customer;
  }

  @Override
  public void deleteCustomer(int id) {
    customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
    customerRepository.deleteById(id);
  }

  @Override
  public String customerPostErrors(BindingResult bindingResult) {
    StringBuilder errors = new StringBuilder("Process failed due to the following error(s):\n");
    for (FieldError fieldError : bindingResult.getFieldErrors())
      errors.append("* ").append(fieldError.getDefaultMessage()).append("\n");
    if (bindingResult.hasFieldErrors("address.street") && bindingResult.hasFieldErrors("address.city") &&
            bindingResult.hasFieldErrors("address.postalCode"))
      errors.append("► Possibly, the address couldn't be found!!!");
    return errors.toString();
  }

  @Override
  public Boolean existsCustomerByUserNameOrEmailOrPhoneNumber(Customer customer) {
    return customerRepository.existsCustomerByUserNameOrEmailOrPhoneNumber(customer.getUserName(), customer.getEmail(),
            customer.getPhoneNumber());
  }


}
