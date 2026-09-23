package com.flmhospitals.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flmhospitals.dto.RegisterPatientRequestDto;
import com.flmhospitals.dto.RegisterPatientResponseDto;
import com.flmhospitals.model.Patient;
import com.flmhospitals.service.PatientService;

@RestController  
@RequestMapping("/patients")
public class PatientController {
	
	public final PatientService patientService;


	public PatientController(PatientService patientService) {
		
		this.patientService = patientService;
	}
	
	@GetMapping()
	public ResponseEntity<List<RegisterPatientResponseDto>> getAllPatients(){
		
		List<RegisterPatientResponseDto> allPatients = patientService.getAllPatients();
		
		return ResponseEntity.status(HttpStatus.OK).body(allPatients);
		
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterPatientResponseDto> regiesterPatient(
			@RequestBody RegisterPatientRequestDto registerPatientRequestDto) {

		RegisterPatientResponseDto regiesterPatientResponse = patientService
				.regiesterPatient(registerPatientRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(regiesterPatientResponse);

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<RegisterPatientResponseDto> updatePatient(@RequestBody  RegisterPatientRequestDto registerPatientRequestDto, @PathVariable(name="id") String patientId){
		
		RegisterPatientResponseDto updatedPatientResponse =patientService.updatePatient(registerPatientRequestDto, patientId);
		
		return ResponseEntity.status(HttpStatus.OK).body(updatedPatientResponse);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RegisterPatientResponseDto> getPatientById(@PathVariable(name="id") String patientId){
		
		RegisterPatientResponseDto patientResponse=patientService.getPatientById(patientId);
		
		return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
		
	}
	
	@GetMapping("/getDoctorPatients/{staffId}")
	public List<Patient> getPatientsVisitedByDoctor(@PathVariable(name="staffId") String staffId, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate){
		
		List<String> listOfPatientIds = patientService.getPatientsVisitedByDoctor(staffId,startDate,endDate);
		
		return patientService.getPatientsByDoctor(listOfPatientIds);
		
	}
	
	@GetMapping("/getPatientName/{patientId}")
	String getPatientName(@PathVariable(name="patientId") String patientId) {
		
		return patientService.getPatientName(patientId);
	}
}
