package com.flmhospitals.builder;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffAddressDto;
import com.flmhospitals.enums.StaffType;
import com.flmhospitals.model.Staff;
import com.flmhospitals.model.StaffAddress;
import com.flmhospitals.model.StaffDetails;

public class StaffBuilder {
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	// ThreadLocal to store plain password temporarily for email notification
	private static final ThreadLocal<String> plainPasswordHolder = new ThreadLocal<>();

	public static Staff buildStaffFromRegisterStaffDto(RegisterStaffDto registerStaffDto){
		
		return Staff.builder()

		.firstName(registerStaffDto.getFirstName())
		.lastName(registerStaffDto.getLastName())
		.phoneNumber(String.valueOf(registerStaffDto.getPhoneNumber()))
		.gender(registerStaffDto.getGender())
		.dateOfJoining(registerStaffDto.getDateOfJoining())
		.staffType(registerStaffDto.getStaffType())
		.specialization(registerStaffDto.getSpecialization())
		.experienceInYears(registerStaffDto.getExperienceInYears())
		.email(registerStaffDto.getEmail())
		.role(registerStaffDto.getRole() != null ? registerStaffDto.getRole().toUpperCase() : null)
		.canLogin(true)
		.isEmployeeActive(true)
		.staffDetails(buildStaffDetailsFromStaffDetailsDto(registerStaffDto.getEmail()))
		.staffAddress(buildStaffAdddressFromStaffAddressDto(registerStaffDto.getStaffAddressDto()))
		.build();

	}
	
	public static StaffAddress buildStaffAdddressFromStaffAddressDto(StaffAddressDto staffAddressDto) {
		

		StaffAddress staffAddress = new StaffAddress();
		
		System.out.println("Staff Address Dto" + staffAddressDto);
		
		 BeanUtils.copyProperties(staffAddressDto, staffAddress);

		return staffAddress;

	}
	
	public static StaffDetails buildStaffDetailsFromStaffDetailsDto(String email) {
		
		String plainPassword = generateSixDigitPassword();
		String hashedPassword = passwordEncoder.encode(plainPassword);
		
		// Store plain password temporarily for email notification
		plainPasswordHolder.set(plainPassword);
		
		return StaffDetails.builder()
				.email(email)
				.password(hashedPassword)
				.requirePasswordReset(true) // Force password reset for new accounts
				.build();
	}
	
	public static String getPlainPassword() {
		return plainPasswordHolder.get();
	}
	
	public static void clearPlainPassword() {
		plainPasswordHolder.remove();
	}
	
	public static String generateSixDigitPassword() {
        Random random = new Random();
        int password = 100000 + random.nextInt(900000);
        return String.valueOf(password);
    }
	
	public static Staff updateStaffBuilder(RegisterStaffDto dto, Staff existingStaff) {
	    existingStaff.setFirstName(dto.getFirstName());
	    existingStaff.setLastName(dto.getLastName());
	    existingStaff.setPhoneNumber(String.valueOf(dto.getPhoneNumber()));
	    existingStaff.setGender(dto.getGender());
	    existingStaff.setDateOfJoining(dto.getDateOfJoining());
	    existingStaff.setStaffType(dto.getStaffType());
	    existingStaff.setSpecialization(dto.getSpecialization());
	    existingStaff.setExperienceInYears(dto.getExperienceInYears());
	    existingStaff.setRole(dto.getRole() != null ? dto.getRole().toUpperCase() : null);
	    existingStaff.setEmail(dto.getEmail());

	    StaffDetails details = existingStaff.getStaffDetails();
	    details.setEmail(dto.getEmail());
	    
	    StaffAddress address = existingStaff.getStaffAddress();
	    StaffAddressDto addrDto = dto.getStaffAddressDto();

	    address.setLandmark(addrDto.getLandmark());
	    address.setCity(addrDto.getCity());
	    address.setState(addrDto.getState());
	    address.setCountry(addrDto.getCountry());
	    address.setPinCode(addrDto.getPinCode());
	    
	    return existingStaff;
	}

}
