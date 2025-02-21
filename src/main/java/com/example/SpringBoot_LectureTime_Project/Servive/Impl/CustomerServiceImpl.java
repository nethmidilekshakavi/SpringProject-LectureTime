package com.example.SpringBoot_LectureTime_Project.Servive.Impl;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Repo.CustomerRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.CustomerService;
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
    public boolean saveCustomer(CustomerDto customerDto) {

       /* String name = "Vimala";

        Customer customer1 =   customerRepo.SearchCustomerName(name);
        System.out.println("=========================="+customer1.getAddress());*/

      if (customerRepo.existsById(customerDto.getId())){
          return false;

      }else {
          customerRepo.save(modelMapper.map(customerDto, Customer.class));
          return true;
      }

    }

    @Override
    public boolean deleteCustomer(Integer id) {
        try {

            Optional<Customer> optionalCustomer = customerRepo.findById(id);

            if (optionalCustomer.isPresent()) {
                customerRepo.deleteById(id);
                return true;
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<CustomerDto> allCustomers() {
        return modelMapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDto>>(){}.getType());
    }

    @Override
    public boolean updateCustomer(Integer id, CustomerDto customer) {

        Customer existingCustomer = customerRepo.findById(id).orElse(null);

        if (existingCustomer != null) {


            existingCustomer.setName(customer.getName());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setContact(customer.getContact());
            existingCustomer.setEmail(customer.getEmail());

            customerRepo.save(existingCustomer);
            return true;

        }
        return false;
    }

    @Override
    public List<Customer> findCustomer(Integer id) {

        List<Customer> res =  customerRepo.findCustomersById(id);

        return res;
    }


}
