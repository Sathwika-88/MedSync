package com.flmhospitals.builder;

import org.springframework.beans.BeanUtils;
import com.flmhospitals.dto.StaffAddressDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.model.Staff;
import com.flmhospitals.model.StaffAddress;

public class StaffDtoBuilder {

	public static StaffDetailsDto buildStaffDetailsDto(Staff staff) {

		return StaffDetailsDto
				.builder()
				.staffId(staff.getStaffId())
				.firstName(staff.getFirstName())
				.lastName(staff.getLastName())
				.phoneNumber(staff.getPhoneNumber())
				.role(staff.getRole())
				.gender(staff.getGender())
				.experienceInYears(staff.getExperienceInYears())
				.dateOfJoining(staff.getDateOfJoining())
				.email(staff.getStaffDetails().getEmail())
				.specialization(staff.getSpecialization())
				.staffType(staff.getStaffType())
				.isEmployeeActive(staff.isEmployeeActive())
				.canLogin(staff.isCanLogin())
				.staffAddressDto(buildAddressDto(staff.getStaffAddress()))
				.build();


	}

	public static StaffAddressDto buildAddressDto(StaffAddress staffAddress) {

		StaffAddressDto staffAddressDto = new StaffAddressDto();

		BeanUtils.copyProperties(staffAddress, staffAddressDto);

		return staffAddressDto;
	}
	
	

}
