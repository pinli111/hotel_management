package com.example.hotel_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    @Query(value = "SELECT room FROM room WHERE status = ?1" ,nativeQuery = true)
    List<String> getAllRoom(Room.Status status);

    @Query(value = "SELECT * FROM room WHERE room = ?1" ,nativeQuery = true)
    Room findByRoom(String room);

    @Query(value = "SELECT MIN(room) FROM room WHERE status = 0" ,nativeQuery = true)
    String getNearestRoom();


}
