package com.codegym.rapphim.service;

import com.codegym.rapphim.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoomService extends IGenerateService<Room>{
    Page<Room> findByRoomNameContaining(String roomName, Pageable pageable);
}
