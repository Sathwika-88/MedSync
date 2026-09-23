package com.flmhospitals.builder;

import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.model.Bed;

public class BedBuilder {

	public static Bed buildBedFromBedRequestDto(BedRequestDTO bedRequestDTO) {
		return Bed.builder()
				  .bedNumber(bedRequestDTO.getBedNumber())
				  .isOccupied(bedRequestDTO.isOccupied())
				  .build();
	}
}
