package com.flmhospitals.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.flmhospitals.dto.BedDetailsResponseDTO;
import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.dto.RoomResponseDto;
import com.flmhospitals.service.BedService;

@RestController
@RequestMapping("/bed")
public class BedController {
	
	private final BedService bedService;

	public BedController(BedService bedService) {
		super();
		this.bedService = bedService;
	}

	@DeleteMapping("/delete-bed/{bedNumber}/{roomNumber}")
	public ResponseEntity<String> removeBed(@PathVariable(name = "bedNumber") long bedNumber,
			@PathVariable(name = "roomNumber") long roomNumber) {

		return bedService.removeBed(bedNumber, roomNumber);

	}

	@PostMapping("/addBed")
	public ResponseEntity<String> addBedInRoom(@RequestBody BedRequestDTO bedRequestDTO) {

		return bedService.addBedInRoom(bedRequestDTO);

	}
	
	@PutMapping("/update-bed/{roomNumber}/{bedNumber}")
	public ResponseEntity<BedDetailsResponseDTO> updateBedDetails(@PathVariable(name="roomNumber") long roomNumber,@PathVariable(name="bedNumber") long bedNumber,@RequestBody BedRequestDTO bedRequestDTO){
		ResponseEntity<BedDetailsResponseDTO> updateBedDetails = bedService.updateBedDetails(roomNumber,bedNumber,bedRequestDTO);
		return updateBedDetails;
	}
	
	@GetMapping("/getBeds")
	public ResponseEntity<List<BedDetailsResponseDTO>> getAllBedDetails(){
		List<BedDetailsResponseDTO> allBedDetails = bedService.getAllBedDetails();
		return ResponseEntity.status(HttpStatus.OK).body(allBedDetails);
		
	}
	@GetMapping("/room/{roomNumber}")
    public ResponseEntity<List<BedDetailsResponseDTO>> getAllBedsInRoom(@PathVariable long roomNumber) {

        return ResponseEntity.ok(bedService.getBedsByRoomId(roomNumber));
    }
	
	@GetMapping("/vacant/room/{roomNumber}")
    public ResponseEntity<List<BedDetailsResponseDTO>> getVacantBeds(@PathVariable long roomNumber) {

        return ResponseEntity.ok(bedService.getVacantBedsByRoomNumber(roomNumber));
    }

}
