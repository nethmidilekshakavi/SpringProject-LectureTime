package com.example.SpringBoot_LectureTime_Project.Servive.Impl;

import com.example.SpringBoot_LectureTime_Project.Dto.OrderDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Entity.Item;
import com.example.SpringBoot_LectureTime_Project.Entity.Order;
import com.example.SpringBoot_LectureTime_Project.Repo.CustomerRepo;
import com.example.SpringBoot_LectureTime_Project.Repo.ItemRepo;
import com.example.SpringBoot_LectureTime_Project.Repo.OrderRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.OrderService;
import com.example.SpringBoot_LectureTime_Project.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Mapping mapping;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public void saveOrder(OrderDto orderDto) {

      Order order = new Order();
      mapping.toOrderEntity(orderDto);

      Customer customer = customerRepo.getReferenceById(orderDto.getCustomerId());

      order.setCustomer(customer);

      List<Item> itemList = new ArrayList<>();

      for (Integer id : orderDto.getItemCodes()){

          if (itemRepo.existsById(id)){

              itemList.add(itemRepo.getReferenceById(id));

          }

          order.setItems(itemList);
          order.setQty(orderDto.getQty());
          order.setTotalPrice(orderDto.getTotalPrice());

      }

      orderRepo.save(order);
        System.out.println(order);

    }
}
