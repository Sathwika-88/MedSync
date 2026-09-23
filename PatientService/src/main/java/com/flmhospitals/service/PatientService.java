package com.flmhospitals.service;

import java.time.LocalDate;
import java.util.List;

import com.flmhospitals.dto.RegisterPatientRequestDto;
import com.flmhospitals.dto.RegisterPatientResponseDto;
import com.flmhospitals.model.Patient;

public interface PatientService {
	
	List<RegisterPatientResponseDto> getAllPatients();

	RegisterPatientResponseDto regiesterPatient(RegisterPatientRequestDto registerPatientRequestDto);
	
	RegisterPatientResponseDto updatePatient(RegisterPatientRequestDto patientRequestDto ,String patientId);
	
	RegisterPatientResponseDto getPatientById(String patientId);

	List<Patient> getPatientsByDoctor(List<String> listOfPatientIds);

	List<String> getPatientsVisitedByDoctor(String staffId, LocalDate startDate, LocalDate endDate);

	String getPatientName(String patientId);
	
}
