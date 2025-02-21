package com.example.SpringBoot_LectureTime_Project.Repo;

import com.example.SpringBoot_LectureTime_Project.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer , Integer> {

    List<Customer> findAllByAddress (String Address);

    //native query
    @Query(value = "select * from Customer" , nativeQuery = true)
    void getAllCustomers();

    @Query(value = "select * from Customer where name=?1" , nativeQuery = true)
    Customer SearchCustomerName(String name);

}
