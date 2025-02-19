package com.example.SpringBoot_LectureTime_Project.Controller;


import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;
import com.example.SpringBoot_LectureTime_Project.Servive.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto){

        itemService.saveItem(itemDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping
    public List<ItemDto> getAllItems(){
     return  itemService.getAllItem();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable ("id") Integer id){

        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
