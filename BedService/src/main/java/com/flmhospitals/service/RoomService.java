package com.flmhospitals.service;

import java.util.List;

import com.flmhospitals.dto.RoomRequestDto;
import com.flmhospitals.dto.RoomResponseDto;

public interface RoomService {

	RoomResponseDto UpdateRoomDetails(long roomNumber, RoomRequestDto roomRequestDto);

	public boolean removeRoom(long roomNumber);

	RoomResponseDto addRoom(RoomRequestDto roomrequestDto);
	List<RoomResponseDto> getAllRoomDetails();
}
