package com.example.SpringBoot_LectureTime_Project.Servive;

import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;

import java.util.List;

public interface ItemService {

    boolean saveItem(ItemDto itemDto);

    List<ItemDto> getAllItem();

    boolean deleteItem(Integer id);

    boolean update(Integer id , ItemDto itemDto);

}
