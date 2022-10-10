package com.example.hotel_management;

import javax.persistence.*;

@Entity
@Table
public class Room {
    enum Status {
        Available,
        Occupied,
        Vacant,
        Repair
    }

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long id;
    private String room;
    private Status status;
    private Integer distance;

    public Room(Long id, String room, Status status, Integer distance) {
        this.id = id;
        this.room = room;
        this.status = status;
        this.distance = distance;
    }

    public Room(String room, Status status, Integer distance) {
        this.room = room;
        this.status = status;
        this.distance = distance;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", status=" + status +
                ", distance=" + distance +
                '}';
    }
}
