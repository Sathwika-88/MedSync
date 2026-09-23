package com.flmhospitals.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flmhospitals.dto.LoginRequest;
import com.flmhospitals.dto.LoginResponse;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.ResetPasswordRequest;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.dto.VerifyOtpRequest;
import com.flmhospitals.model.Staff;

public interface StaffService {
	
	 Staff getStaffByStaffId(String staffId);

	 ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(String name);
	 
	 StaffDetailsDto updateStaff(String staffId,RegisterStaffDto dto);
	
	 StaffDetailsDto registerStaffDeatils(RegisterStaffDto registerStaffDto);

	 String deleteStaff(String staffId);

	 String getDoctorName(String doctorId);
	 
	 String getSpecialization(String staffId);

	 List<StaffDetailsDto> getAllStaff();

	 void sendOtp(String email);

	 void verifyOtp(VerifyOtpRequest request);

	 void resetPassword(ResetPasswordRequest request);  
	 
	 LoginResponse login(LoginRequest request);
		
}

