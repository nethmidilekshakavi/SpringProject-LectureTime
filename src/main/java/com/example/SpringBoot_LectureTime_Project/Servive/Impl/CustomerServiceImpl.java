package com.example.SpringBoot_LectureTime_Project.Servive.Impl;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Repo.CustomerRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.CustomerService;
import com.example.SpringBoot_LectureTime_Project.Util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private Mapping mapping;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        System.out.println(" ============================"+customerDto);
        Customer customer = mapping.toCustomerEntity(customerDto);
        customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        try{

            Optional<Customer> optionalCustomer = customerRepo.findById(id);

            if (optionalCustomer.isPresent()) {

                customerRepo.deleteById(id);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<CustomerDto> allCustomers() {

        List<CustomerDto> customerDtoList = mapping.asCustomerDtoList(customerRepo.findAll());

        return customerDtoList;

    }

    @Override
    public void updateCustomer(Integer id, CustomerDto customerDto) {

     Optional<Customer> optionalCustomer =    customerRepo.findById(id);

     Customer customer = new Customer();

            if (optionalCustomer.isPresent()){

             customerDto.setName(customer.getName());
             customerDto.setAddress(customer.getAddress());
             customerDto.setContact(customer.getContact());
             customerDto.setEmail(customer.getEmail());

            }

            customerRepo.save(customer);
    }


}
