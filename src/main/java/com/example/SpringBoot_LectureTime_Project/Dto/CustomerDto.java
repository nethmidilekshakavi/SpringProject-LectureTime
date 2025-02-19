package com.example.SpringBoot_LectureTime_Project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    private int id;
    private String name;
    private String address;
    private int contact;
    private String email;

}
