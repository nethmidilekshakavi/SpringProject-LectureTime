package com.example.SpringBoot_LectureTime_Project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Integer orderId;
    private Integer customerId;
    private List<Integer> itemCodes;
    private double totalPrice;
    private int qty;
}
