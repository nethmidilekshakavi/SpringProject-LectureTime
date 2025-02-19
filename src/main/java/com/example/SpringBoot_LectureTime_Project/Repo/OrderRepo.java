package com.example.SpringBoot_LectureTime_Project.Repo;

import com.example.SpringBoot_LectureTime_Project.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order , Integer> {
}
