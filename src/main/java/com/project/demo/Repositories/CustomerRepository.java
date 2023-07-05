package com.project.demo.Repositories;

import com.project.demo.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  Boolean existsCustomerByUserNameOrEmailOrPhoneNumber (String userName, String email, String phoneNumber);
}
