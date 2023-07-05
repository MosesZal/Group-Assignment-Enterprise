package com.project.demo.Services;

import com.project.demo.Entities.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import java.util.List;

@Repository
public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer updateCustomer(Customer customer);
    ResponseEntity<String> customerAddingLogic(Customer customer, BindingResult bindingResult);
    Customer findByID (int ID);
    void deleteCustomer(int id);
    String customerPostErrors(BindingResult bindingResult);
    Boolean existsCustomerByUserNameOrEmailOrPhoneNumber (Customer customer);
    ResponseEntity<String>customerUpdatingLogic(Customer customer);
    ResponseEntity<String>customerDeleteLogic(Customer customer);
}
