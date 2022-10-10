package com.example.hotel_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RoomService {
    private final RoomRepo roomRepo;

    @Autowired
    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<Room> getAllRoom()
    {
        return roomRepo.findAll();
    }

    // it is better has the distance to get which nearest instead of checking everytime
    public String getAssignedRoom()
    {
        List<String> allAvailableRoom = roomRepo.getAllRoom(Room.Status.Available);
        /*
        List<String> words = new ArrayList<String>();
        for(String str : allAvailableRoom)
        {
            System.out.println("String " + str);
            Matcher match = Pattern.compile("[1-4]+[A-E]+").matcher(str);
            while(match.find())
            {
                words.add(match.group());
            }

            char level = str.charAt(0);
            char unit = str.charAt(1);
            System.out.println("level: "  + level + " unit: " + unit);
        }
        for(String word : words)
        {
            System.out.println(word + " ");
        }
*/
        if(allAvailableRoom.isEmpty())
        {
            throw new IllegalStateException("There is no available room!");
        }
        return roomRepo.getNearestRoom();
    }
    public List<String> getRoom(Room.Status status)
    {
        return roomRepo.getAllRoom(status);
    }

    public void updateStatus(String room, Room.Status statusFrom, Room.Status statusTo)
    {
        Room roomByName = roomRepo.findByRoom(room);
        if(roomByName == null)
        {
            throw new IllegalStateException("RoomNo " + room + " not exist");
        }

        if(roomByName.getStatus() != statusFrom)
        {
            throw new IllegalStateException("Currently the room is " + roomByName.getStatus() + " . It cannot be marked as " + statusTo );
        }
        else {
            roomByName.setStatus(statusTo);
        }
        roomRepo.save(roomByName);
    }
}
