package com.flmhospitals.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "staff_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffAddress {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffAddressId;

    private String landmark;

    private String city;

    private String state;

    private String country;

    private String pinCode;

	public StaffAddress(String landmark, String city, String state, String country, String pinCode) {
		super();
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}
    
    


}
