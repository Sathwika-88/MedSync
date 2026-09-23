package com.flmhospitals.controller;


import java.util.List;
import com.flmhospitals.service.impl.RoomServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flmhospitals.dto.RoomRequestDto;
import com.flmhospitals.dto.RoomResponseDto;
import com.flmhospitals.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	private final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping("/addroom")
	public ResponseEntity<RoomResponseDto> addRoom(@RequestBody RoomRequestDto roomRequestDto) {

		RoomResponseDto roomResponseDto = roomService.addRoom(roomRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(roomResponseDto);

	}

	@DeleteMapping("/delete-rooms/{roomNumber}")
	public ResponseEntity<Boolean> removeRoom(@PathVariable("roomNumber") long roomNumber) {
		return ResponseEntity.ok(roomService.removeRoom(roomNumber));
	}

	@PutMapping("/update-rooms/{roomNumber}")
	public ResponseEntity<RoomResponseDto> updateRoomDetails(@PathVariable long roomNumber,
			@RequestBody RoomRequestDto roomRequestDto) {
		RoomResponseDto updatedRoomDetails = roomService.UpdateRoomDetails(roomNumber, roomRequestDto);
		return ResponseEntity.ok(updatedRoomDetails);
		
	}
	@GetMapping("/getRooms")
	public ResponseEntity<List<RoomResponseDto>> getAllRoomDetails(){
		List<RoomResponseDto> allRoomDetails = roomService.getAllRoomDetails();
		return ResponseEntity.status(HttpStatus.OK).body(allRoomDetails);
		
	}


}

