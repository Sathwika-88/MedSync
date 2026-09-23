package com.flmhospitals.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flmhospitals.model.Patient;

import jakarta.persistence.PrePersist;

@Component
public class PatientEntityListner {
	
	public static PatientIdGenerator patientIdGenerator;
		
	@Autowired
	public void init(PatientIdGenerator patientIdGenerators) {
	
		patientIdGenerator = patientIdGenerators;
	}
	
	@PrePersist
	public void generatePatientId(Patient patient){
		if (patient.getPatientId()==null) {
			patient.setPatientId(patientIdGenerator.generateNextPatientId());
			}
}
}
