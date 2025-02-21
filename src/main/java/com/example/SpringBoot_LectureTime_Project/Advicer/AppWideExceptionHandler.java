package com.example.SpringBoot_LectureTime_Project.Advicer;

import com.example.SpringBoot_LectureTime_Project.Util.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseUtil exceptionHandler(Exception e){
        return new ResponseUtil(
                500,"Internal Server Error",e.getMessage()
        );
    }

}
