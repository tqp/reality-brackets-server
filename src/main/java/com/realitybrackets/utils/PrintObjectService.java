package com.realitybrackets.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PrintObjectService {

    public void PrintObject(String title, Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(title + ":\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
