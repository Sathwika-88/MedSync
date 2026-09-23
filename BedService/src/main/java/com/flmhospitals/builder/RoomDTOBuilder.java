package com.flmhospitals.builder;


import java.util.ArrayList;
import java.util.List;
import com.flmhospitals.dto.BedDetailsResponseDTO;
import com.flmhospitals.dto.RoomResponseDto;
import com.flmhospitals.model.Bed;
import com.flmhospitals.model.Room;

public class RoomDTOBuilder {
	
	public static RoomResponseDto buildRoomResponseDtofromRoom(Room room) {
		
		return RoomResponseDto
				.builder()
				.roomNumber(room.getRoomNumber())
				.roomType(room.getRoomType())
				.roomCapacity(room.getRoomCapacity())
				.beds(buildBedDetailsResponseDtos(room.getBeds()))
				.build();
	}

	private static List<BedDetailsResponseDTO> buildBedDetailsResponseDtos(List<Bed> beds) {

		List<BedDetailsResponseDTO> bedDetailsResponseDto = new ArrayList<>();
		
		for (Bed bed : beds) {
			bedDetailsResponseDto.add(BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(bed));
		}
		
		return bedDetailsResponseDto;
	}
}
