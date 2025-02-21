package com.example.SpringBoot_LectureTime_Project.Controller;

import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Item;
import com.example.SpringBoot_LectureTime_Project.Repo.ItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/findItemDetails")
@CrossOrigin
public class FindItem {


    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ItemDto FindItemDetails(@PathVariable ("id") Integer id){

        Optional<Item> optionalItem = itemRepo.findById(id);

        if (optionalItem.isPresent()){

            return modelMapper.map(optionalItem.get(), ItemDto.class);

        }else {

            new RuntimeException("Item not Found");
        }
        return null;
    }

}
