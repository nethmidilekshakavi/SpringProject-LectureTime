package com.example.SpringBoot_LectureTime_Project.Servive;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDto customerDto);

    void deleteCustomer(Integer id);

    List<CustomerDto> allCustomers();

    void updateCustomer(Integer id , Customer customer);

}
