package com.example.SpringBoot_LectureTime_Project.Servive.Impl;
import com.example.SpringBoot_LectureTime_Project.Dto.ItemDto;
import com.example.SpringBoot_LectureTime_Project.Entity.Item;
import com.example.SpringBoot_LectureTime_Project.Repo.ItemRepo;
import com.example.SpringBoot_LectureTime_Project.Servive.ItemService;
import com.example.SpringBoot_LectureTime_Project.Util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private Mapping mapping;
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public void saveItem(ItemDto itemDto) {

        Item item = mapping.toItemEntity(itemDto);
        itemRepo.save(item);

    }

    @Override
    public List<ItemDto> getAllItem() {

        List<ItemDto> allItems = mapping.asItemDtoList(itemRepo.findAll());
        return allItems;

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

}
