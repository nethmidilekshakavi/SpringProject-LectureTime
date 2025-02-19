package com.example.SpringBoot_LectureTime_Project.Util;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;
import com.example.SpringBoot_LectureTime_Project.Dto.OrderDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Entity.Item;
import com.example.SpringBoot_LectureTime_Project.Entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public Customer toCustomerEntity(CustomerDto customerDto){

        return modelMapper.map(customerDto , Customer.class);

    }

    public List<CustomerDto> asCustomerDtoList(List<Customer>customerEntityList){
        return modelMapper.map(customerEntityList, new TypeToken<List<CustomerDto>>(){}.getType());
    }


    public CustomerDto toCustomerDTO(Customer customerEntity){
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
    public Item toItemEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, Item.class);
    }

    public List<ItemDto> asItemDtoList(List<Item>itemEntity){
        return modelMapper.map(itemEntity, new TypeToken<List<ItemDto>>(){}.getType());
    }

    public Order toOrderEntity(OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }



}
