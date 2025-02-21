package com.example.SpringBoot_LectureTime_Project.Controller;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Repo.CustomerRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.CustomerService;
import com.example.SpringBoot_LectureTime_Project.Util.Mapping;
import com.example.SpringBoot_LectureTime_Project.Util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v2/customerFind")
public class FindCustomer {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Integer id) {
        Optional<Customer> customerOpt = customerRepo.findById(id);

        if (customerOpt.isPresent()) {
            return modelMapper.map(customerOpt.get(), CustomerDto.class);
        } else {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }
}
