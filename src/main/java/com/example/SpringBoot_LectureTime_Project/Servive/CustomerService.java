package com.example.SpringBoot_LectureTime_Project.Servive;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;

import java.util.List;

public interface CustomerService {

    boolean saveCustomer(CustomerDto customerDto);

    boolean deleteCustomer(Integer id);

    List<CustomerDto> allCustomers();

    boolean updateCustomer(Integer id , CustomerDto customer);

    List<Customer> findCustomer (Integer id);

}
