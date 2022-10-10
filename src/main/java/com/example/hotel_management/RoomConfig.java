package com.example.hotel_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Configuration
public class RoomConfig {

    @Bean
    CommandLineRunner commandLineRunner(RoomRepo roomRepo)
    {
        return args -> {
            HashMap<String, Integer> map = new HashMap<String,Integer>();
            for(int i=1; i< 5; i++)
            {
                int count = 0;
                count = i % 2 == 1 ? (i-1) * 5 + 1 : i*5;
                for(int j=65; j< 70; j++)
                {
                    String roomName = "" + i + (char)j;
                    System.out.println(roomName);
                    System.out.println(count);
                    map.put(roomName, count);
                    Room room;
                    if(i % 2 == 1)
                    {
                        room = new Room(roomName, Room.Status.Vacant, count);
                        count++;
                    }
                    else
                    {
                        room = new Room(roomName, Room.Status.Available, count);
                        count--;
                    }
                    roomRepo.save(room);
                }
            }
        };
    }
}
