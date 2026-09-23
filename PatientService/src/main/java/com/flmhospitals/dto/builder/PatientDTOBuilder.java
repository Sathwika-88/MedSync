package com.flmhospitals.dto.builder;

import java.util.List;

import com.flmhospitals.dto.PatientAddressResponseDto;
import com.flmhospitals.dto.RegisterPatientResponseDto;
import com.flmhospitals.model.Patient;
import com.flmhospitals.model.PatientAddress;

public class PatientDTOBuilder {
	

	public static RegisterPatientResponseDto fromPatientEntityToRegPatientRespDtO(Patient patient) {
		
		return RegisterPatientResponseDto.builder()
		.patientId(patient.getPatientId())
		.patientName(patient.getPatientName())
		.gender(patient.getGender())
		.patientEmail(patient.getPatientEmail())
		.patientPhoneNumber(patient.getPatientPhoneNumber())
		.dateOfBirth(patient.getDateOfBirth())
		.patientAddress(patient.getPatientAddress()!=null ? (fromPatientAddressToPatRespDto(patient.getPatientAddress())): null)
		.build();
	}
	
	public static PatientAddressResponseDto fromPatientAddressToPatRespDto(PatientAddress patientAddress) {
		return PatientAddressResponseDto.builder()
		.patientAddressId(patientAddress.getPatientAddressId())
		.doorNumber(patientAddress.getDoorNumber())
		.landmark(patientAddress.getLandmark())
		.city(patientAddress.getCity())
		.state(patientAddress.getState())
		.country(patientAddress.getCountry())
		.pinCode(patientAddress.getPinCode())
		.build();
	}
	
	public static List<RegisterPatientResponseDto> fromListOfPatientToListOfRegPatientRespDto(List<Patient> patients){
		
		return patients.stream().map(patient -> fromPatientEntityToRegPatientRespDtO(patient)).toList();
	}
	
	
}
