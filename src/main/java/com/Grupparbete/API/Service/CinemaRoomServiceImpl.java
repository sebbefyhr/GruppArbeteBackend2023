package com.Grupparbete.API.Service;


import com.Grupparbete.API.DAO.CinemaRoomRepository;
import com.Grupparbete.API.Entities.CinemaRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CinemaRoomServiceImpl implements CinemaRoomService{
  @Autowired
  CinemaRoomRepository roomRepository;

    @Override
    public CinemaRoom saveRoom(CinemaRoom room) {
        System.out.println("Saved room");
        return roomRepository.save(room);
    }

    @Override
    public Optional<CinemaRoom> findById(int id) {
        return roomRepository.findById(id);
    }
}

