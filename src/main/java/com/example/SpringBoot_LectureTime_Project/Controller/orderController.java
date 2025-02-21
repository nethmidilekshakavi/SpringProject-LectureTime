package com.example.SpringBoot_LectureTime_Project.Controller;

import com.example.SpringBoot_LectureTime_Project.Dto.OrderDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Order;
import com.example.SpringBoot_LectureTime_Project.Servive.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/order")
@CrossOrigin("*")
public class orderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto){

        orderService.saveOrder(orderDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
