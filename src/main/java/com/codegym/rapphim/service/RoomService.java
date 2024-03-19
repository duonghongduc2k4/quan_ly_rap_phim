package com.codegym.rapphim.service;

import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class RoomService implements IRoomService {
@Autowired
private IRoomRepository iRoomRepository;
    @Override
    public Iterable<Room> fillAll() {
        return iRoomRepository.findAll();
    }

    @Override
    public Optional<Room> fillById(int id) {
        return iRoomRepository.findById(id);
    }

    @Override
    public void save(Room room) {
iRoomRepository.save(room);
    }

    @Override
    public void remote(int id) {
iRoomRepository.deleteById(id);
    }
}
