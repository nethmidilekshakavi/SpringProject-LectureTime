package com.example.SpringBoot_LectureTime_Project.Controller;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Repo.CustomerRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.CustomerService;
import com.example.SpringBoot_LectureTime_Project.Util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/customer")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ResponseUtil saveCustomer(@RequestBody CustomerDto customerDto){
        System.out.println(customerDto);

        boolean res =    customerService.saveCustomer(customerDto);

        try{

            if (res){
                return new ResponseUtil(201,"Customer Save",null);
            }

            else {
                return new ResponseUtil(500,"Customer not save",null);
            }

        } catch (Exception e) {
            throw new RuntimeException();


        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseUtil deleteCustomer(@PathVariable ("id") Integer id){

        try {
            Optional<Customer> optionalCustomer = customerRepo.findById(id);
            if (optionalCustomer.isPresent()) {
                boolean res = customerService.deleteCustomer(id);

                if (res) {
                    return new ResponseUtil(201, "Customer Delete ok", null);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseUtil(500,"Customer not Delete",null);
    }


    @GetMapping
    public List<CustomerDto> getAllCustomer(){

      return    modelMapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDto>>(){}.getType());



    }

    @PutMapping(value = "/{id}")
    public ResponseUtil updateCustomer(@PathVariable ("id") Integer id,
                                                @RequestBody CustomerDto customerDto
                                               ){

        boolean res = customerService.updateCustomer(id,customerDto);

        if (res){
            return new ResponseUtil(201,"Customer save Ok",null);
        }

        return new ResponseUtil(500,"Customer Not Update",null);

    }

}
