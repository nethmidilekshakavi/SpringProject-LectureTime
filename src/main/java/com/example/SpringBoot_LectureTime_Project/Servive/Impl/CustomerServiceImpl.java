package com.example.SpringBoot_LectureTime_Project.Servive.Impl;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Repo.CustomerRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.CustomerService;
import com.example.SpringBoot_LectureTime_Project.Util.Mapping;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerRepo.save(modelMapper.map(customerDto, Customer.class));
    }

    @Override
    public void deleteCustomer(Integer id) {
        try {

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
        return modelMapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDto>>(){}.getType());
    }

    @Override
    public void updateCustomer(Integer id, Customer customer) {

        Customer existingCustomer = customerRepo.findById(id).orElse(null);

        if (existingCustomer != null) {

            customerRepo.save(modelMapper.map(customer, Customer.class));

        }
    }

}
