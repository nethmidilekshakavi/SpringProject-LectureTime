package com.example.SpringBoot_LectureTime_Project.Repo;

import com.example.SpringBoot_LectureTime_Project.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item , Integer> {

    List<Item> findItemById(int id);

}
