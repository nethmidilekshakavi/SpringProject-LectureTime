package com.example.SpringBoot_LectureTime_Project.Repo;

import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer , Integer> {

}
