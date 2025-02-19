package com.example.SpringBoot_LectureTime_Project.Util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebCongif {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
