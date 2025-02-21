package com.example.SpringBoot_LectureTime_Project.Servive.Impl;
import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Entity.Item;
import com.example.SpringBoot_LectureTime_Project.Repo.ItemRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.ItemService;
import com.example.SpringBoot_LectureTime_Project.Util.Mapping;
import com.example.SpringBoot_LectureTime_Project.Util.ResponseUtil;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public boolean saveItem(ItemDto itemDto) {

        if (itemRepo.existsById(itemDto.getId())){
            return false;
        }else {
            itemRepo.save(modelMapper.map(itemDto, Item.class));
            return true;
        }

    }

    @Override
    public List<ItemDto> getAllItem() {

        return modelMapper.map(itemRepo.findAll(), new TypeToken<List<Item>>(){}.getType());

    }

    @Override
    public boolean deleteItem(Integer id) {

        Optional<Item> optionalItem = itemRepo.findById(id);
        try {

            if (optionalItem.isPresent()) {

                itemRepo.deleteById(id);
                return true;

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
return false;
    }

    @Override
    public boolean update(Integer id, ItemDto itemDto) {


            Item existingItem = itemRepo.findById(id).orElse(null);

            if (existingItem != null) {

                existingItem.setPrice(itemDto.getPrice());
                existingItem.setName(itemDto.getName());
                existingItem.setQty(itemDto.getQty());



                // Save the updated item
                itemRepo.save(existingItem);
                return true;
            } else {
                return false;
            }
        }





}
