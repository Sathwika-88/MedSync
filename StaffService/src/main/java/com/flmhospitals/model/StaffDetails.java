package com.flmhospitals.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffDetailsId;

    private String email;

    private String password;
    
    private String resetOtp;
    
    private LocalDateTime otpExpiryTime;
    
    private boolean requirePasswordReset = false;

	public StaffDetails(String email, String password, StaffAddress address, String resetOtp, LocalDateTime otpExpiryTime) {
		super();
		this.email = email;
		this.password = password;
		this.resetOtp = resetOtp;
		this.otpExpiryTime = otpExpiryTime;
	}

	public StaffDetails(String email) {
		super();
		this.email = email;
	}
    
	
    
}