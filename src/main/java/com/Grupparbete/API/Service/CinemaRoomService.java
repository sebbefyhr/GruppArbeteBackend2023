package com.Grupparbete.API.Service;

import com.Grupparbete.API.Entities.CinemaRoom;

import java.util.Optional;

public interface CinemaRoomService {

        CinemaRoom saveRoom(CinemaRoom room);

        Optional<CinemaRoom> findById(int id);
    }

