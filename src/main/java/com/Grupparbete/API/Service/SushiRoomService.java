package com.Grupparbete.API.Service;

import com.Grupparbete.API.Entities.SushiRoom;

import java.util.List;

public interface SushiRoomService {

    SushiRoom addRoom(SushiRoom rooms);
    List<SushiRoom> findAllRooms();
    SushiRoom findRoomById(int id);
    SushiRoom updateRoom(SushiRoom rooms, int id);
    void deleteRoom(int id);
    int getGuestsInRoom(int roomId);
}

