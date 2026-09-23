package com.flmhospitals.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Rooms")
@Builder
public class Room {
	
	@Id
	private long roomNumber;
	
	private String roomType;
	
	private long roomCapacity;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<Bed> beds;

	public Room(String roomType, long roomCapacity, List<Bed> beds) {
	
		this.roomType = roomType;
		
		this.roomCapacity = roomCapacity;
		
		this.beds = beds;
	}
}
