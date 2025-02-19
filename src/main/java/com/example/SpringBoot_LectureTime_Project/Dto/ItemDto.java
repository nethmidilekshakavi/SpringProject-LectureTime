package com.example.SpringBoot_LectureTime_Project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemDto {
    private int id;
    private String name;
    private int qty;
    private double price;
}
