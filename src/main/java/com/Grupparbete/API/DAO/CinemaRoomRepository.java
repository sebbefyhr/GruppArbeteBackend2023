package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom,Integer> {

        }
