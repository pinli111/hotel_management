package com.example.hotel_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hotel")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Get all room
    @GetMapping(path="getallroom")
    public List<Room> getRoom()
    {
        return roomService.getAllRoom();
    }

    // Return the nearest room
    @GetMapping(path="getassignedroom")
    public String getAssignedRoom()
    {
        String assignedRoom = roomService.getAssignedRoom();
        roomService.updateStatus(assignedRoom, Room.Status.Available, Room.Status.Occupied);
        return assignedRoom;
    }

    // Checkout from occupied room to vacant
    @PutMapping(path="checkout/{room}")
    public void checkout(
            @PathVariable("room") String room
            )
    {
        roomService.updateStatus(room, Room.Status.Occupied , Room.Status.Vacant);
    }

    // Mark a room cleaned/available
    @PutMapping(path="clean/{room}")
    public void clean(
            @PathVariable("room") String room
    )
    {
        roomService.updateStatus(room, Room.Status.Vacant , Room.Status.Available);
    }

    // Mark a vacant room to repair
    @PutMapping(path="repair/{room}")
    public void repair(
            @PathVariable("room") String room
    )
    {
        roomService.updateStatus(room, Room.Status.Vacant , Room.Status.Repair);
    }

    // Get a list of available rooms
    @GetMapping(path="availableroom")
    public List<String> getAllAvailableRoom()
    {
        return roomService.getRoom(Room.Status.Available);
    }

}
