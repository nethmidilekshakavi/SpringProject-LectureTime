package com.example.SpringBoot_LectureTime_Project.Controller;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Repo.CustomerRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/customer")
@CrossOrigin(origins = "*")
public class CustomerController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;


    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto){
        System.out.println(customerDto);
        try{
           customerService.saveCustomer(customerDto);
           return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("id") Integer id){

        try{
         Optional<Customer> optionalCustomer =  customerRepo.findById(id);
            if (optionalCustomer.isPresent()){
                customerService.deleteCustomer(id);
            }
          return   new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return   new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping
    public List<CustomerDto> getAllCustomer(){

       List<CustomerDto> customers = customerService.allCustomers();

       return customers;

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable ("id") Integer id,
                                                @RequestPart ("name") String name,
                                                @RequestPart ("address") String address,
                                               @RequestPart ("contact") Integer contact,
                                               @RequestPart ("email") String email
                                               ){
       try{

          CustomerDto customer = new CustomerDto();

          customer.setName(name);
          customer.setAddress(address);
          customer.setEmail(email);
          customer.setContact(contact);

          customerService.updateCustomer(id , customer);

           return new ResponseEntity<>(HttpStatus.OK);

       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }


    }

}
