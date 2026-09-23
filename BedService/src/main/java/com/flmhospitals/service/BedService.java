package com.flmhospitals.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flmhospitals.dto.BedDetailsResponseDTO;
import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.dto.RoomResponseDto;

public interface BedService {

	ResponseEntity<String> removeBed(long bedNumber, long roomNumber);

	ResponseEntity<String> addBedInRoom(BedRequestDTO bedRequestDTO);

	ResponseEntity<BedDetailsResponseDTO> updateBedDetails(long roomNumber, long bedNumber, BedRequestDTO bedRequestDTO);
	
	List<BedDetailsResponseDTO> getBedsByRoomId(long roomNumber);
	
	List<BedDetailsResponseDTO> getVacantBedsByRoomNumber(long roomNumber);

	List<BedDetailsResponseDTO> getAllBedDetails();

}
