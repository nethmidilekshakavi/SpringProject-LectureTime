package com.example.SpringBoot_LectureTime_Project.Servive.Impl;
import com.example.SpringBoot_LectureTime_Project.Dto.CustomerDto;
import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import com.example.SpringBoot_LectureTime_Project.Entity.Item;
import com.example.SpringBoot_LectureTime_Project.Repo.ItemRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.ItemService;
import com.example.SpringBoot_LectureTime_Project.Util.Mapping;
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
    public void saveItem(ItemDto itemDto) {

        itemRepo.save(modelMapper.map(itemDto, Item.class));

    }

    @Override
    public List<ItemDto> getAllItem() {

        return modelMapper.map(itemRepo.findAll(), new TypeToken<List<CustomerDto>>(){}.getType());

    }

    @Override
    public void deleteItem(Integer id) {

        Optional<Item> optionalItem = itemRepo.findById(id);
        try {

            if (optionalItem.isPresent()) {

                itemRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(Integer id, ItemDto itemDto) {

        Item item = itemRepo.getReferenceById(id);

        item.setName(itemDto.getName());
        item.setQty(itemDto.getQty());
        item.setPrice(itemDto.getPrice());

        itemRepo.save(item);
        return true;

    }


}
