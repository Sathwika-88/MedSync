package com.flmhospitals.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="AppointmentManagement")
public interface AppointmentClient {

	@GetMapping("/appointments/getDoctorPatients/{staffId}")
	public List<String> getPatientsVisitedByDoctor(@PathVariable(name="staffId") String staffId, @RequestParam("startDate") String startdate,@RequestParam("endDate") String enddate);
}
