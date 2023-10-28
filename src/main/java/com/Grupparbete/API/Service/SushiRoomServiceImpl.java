package com.Grupparbete.API.Service;

import com.Grupparbete.API.DAO.SushiRoomRepository;
import com.Grupparbete.API.Entities.SushiBooking;
import com.Grupparbete.API.Entities.SushiRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SushiRoomServiceImpl implements SushiRoomService{

    private SushiRoomRepository roomRepository;

    @Autowired
    public SushiRoomServiceImpl(SushiRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public SushiRoom addRoom(SushiRoom room) {
        return roomRepository.save(room);
    }

    @Override
    public List<SushiRoom> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public SushiRoom findRoomById(int id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public SushiRoom updateRoom(SushiRoom updatedRoom, int id) {
        SushiRoom existingRoom = findRoomById(id);
        if (existingRoom != null) {
            existingRoom.setName(updatedRoom.getName());
            existingRoom.setDescription(updatedRoom.getDescription());
            existingRoom.setMaxGuests(updatedRoom.getMaxGuests());
            return roomRepository.save(existingRoom);
        }
        return null;
    }

    @Override
    public int getGuestsInRoom(int roomId) {
        SushiRoom room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {

            int totalGuests = 0;
            for (SushiBooking booking : room.getBookings()) {
                totalGuests += booking.getGuests();
            }
            return totalGuests;
        } else {

            return -1;
        }
    }

    @Override
    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }
}

