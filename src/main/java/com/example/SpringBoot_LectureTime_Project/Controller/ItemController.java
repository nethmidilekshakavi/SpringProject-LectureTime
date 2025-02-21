package com.example.SpringBoot_LectureTime_Project.Controller;


import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;
import com.example.SpringBoot_LectureTime_Project.Servive.ItemService;
import com.example.SpringBoot_LectureTime_Project.Util.ResponseUtil;
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
    public  ResponseUtil saveItem(@RequestBody ItemDto itemDto) {

        try {
            boolean res = itemService.saveItem(itemDto);

            if (res) {
                return new ResponseUtil(201, "Item Save OK", null);
            } else {
                return new ResponseUtil(500, "INTERNAL ERROR", null);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<ItemDto> getAllItems(){
     return  itemService.getAllItem();

    }

    @DeleteMapping("/{id}")
    public ResponseUtil deleteItem(@PathVariable ("id") Integer id){

        boolean res =  itemService.deleteItem(id);

        if (res){
            return new ResponseUtil(201,"Item Delete Susess",null);
        }else {
            return new ResponseUtil(404,"Item id not Found ",null);
        }


    }

    @PutMapping("/{id}")
    public ResponseUtil updateItem(@PathVariable ("id") Integer id ,@RequestBody ItemDto itemDto){
      try{

          boolean RES =  itemService.update(id,itemDto);

          System.out.println(RES);

          if (RES){
              return new ResponseUtil(201,"Item Update OK",null);
          }else {
              return new ResponseUtil(500,"Internal Server Eorrr",null);
          }


      } catch (Exception e) {
          throw new RuntimeException(e);
      }

    }

}
