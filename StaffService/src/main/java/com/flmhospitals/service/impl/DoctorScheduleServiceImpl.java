package com.flmhospitals.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flmhospitals.dao.DoctorScheduleRepository;
import com.flmhospitals.dao.StaffRepository;
import com.flmhospitals.exception.DoctorUnavailableException;
import com.flmhospitals.exception.StaffNotFoundException;
import com.flmhospitals.model.DoctorSchedule;
import com.flmhospitals.model.Staff;
import com.flmhospitals.service.DoctorScheduleService;
import com.flmhospitals.service.StaffService;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

	private final DoctorScheduleRepository doctorScheduleRepository;

	private final StaffRepository staffRepository;

	private final StaffService staffService;

	public DoctorScheduleServiceImpl(DoctorScheduleRepository doctorScheduleRepository, StaffRepository staffRepository,
			StaffService staffService) {

		this.doctorScheduleRepository = doctorScheduleRepository;

		this.staffRepository = staffRepository;

		this.staffService = staffService;
	}

	@Override
	public ResponseEntity<String> markDoctorAvailable(String staffId, List<LocalDate> dates) {

	    // Validate input
	    if (dates == null || dates.isEmpty()) {
	        throw new IllegalArgumentException("Please provide at least one date");
	    }

	    // Validate staff exists and is a doctor
	    Staff staff = staffRepository.findById(staffId)
	            .orElseThrow(() -> new StaffNotFoundException("Staff with ID " + staffId + " not found"));

	    if (staff.getStaffType() != com.flmhospitals.enums.StaffType.DOCTOR) {
	        throw new IllegalArgumentException("Staff with ID " + staffId + " is not a doctor");
	    }

	    LocalDate today = LocalDate.now();
	    List<LocalDate> invalidDates = new ArrayList<>();
	    List<LocalDate> processedDates = new ArrayList<>();

	    // Validate all dates are not in the past
	    for (LocalDate date : dates) {
	        if (date.isBefore(today)) {
	            invalidDates.add(date);
	        }
	    }

	    if (!invalidDates.isEmpty()) {
	        throw new IllegalArgumentException("Cannot mark availability for past dates: " + invalidDates);
	    }

	    // Remove unavailable entries for these dates (making doctor available)
	    for (LocalDate date : dates) {
	        Optional<DoctorSchedule> existingSchedule = 
	                doctorScheduleRepository.findByStaff_StaffIdAndUnavailableDate(staffId, date);
	        
	        if (existingSchedule.isPresent()) {
	            doctorScheduleRepository.delete(existingSchedule.get());
	            processedDates.add(date);
	        }
	    }

	    if (processedDates.isEmpty()) {
	        return ResponseEntity.ok("Doctor is already available on all selected dates");
	    }

	    return ResponseEntity.ok("Doctor marked available for " + processedDates.size() + " date(s)");
	}

	@Override
	public boolean isDoctorAvailable(String staffId, LocalDate date) {

		// Validate staff exists and is a doctor
		Staff staff = staffRepository.findById(staffId)
		        .orElseThrow(() -> new StaffNotFoundException("Staff with ID " + staffId + " not found"));

		if (staff.getStaffType() != com.flmhospitals.enums.StaffType.DOCTOR) {
		    throw new IllegalArgumentException("Staff with ID " + staffId + " is not a doctor");
		}

		// Doctor is available if there's NO unavailable entry for this date
		boolean isAvailable = !doctorScheduleRepository.existsByStaff_StaffIdAndUnavailableDate(staffId, date);

		return isAvailable;

	}

	@Override
	public List<LocalDate> markDoctorUnavailable(String doctorId, List<LocalDate> listOfUnavailabeDates) {

	    // Validate input
	    if (listOfUnavailabeDates == null || listOfUnavailabeDates.isEmpty()) {
	        throw new IllegalArgumentException("Please provide at least one date");
	    }

	    // Validate staff exists and is a doctor
	    Staff staff = staffRepository.findById(doctorId)
	            .orElseThrow(() -> new StaffNotFoundException("Staff with ID " + doctorId + " not found"));

	    if (staff.getStaffType() != com.flmhospitals.enums.StaffType.DOCTOR) {
	        throw new IllegalArgumentException("Staff with ID " + doctorId + " is not a doctor");
	    }

	    LocalDate today = LocalDate.now();
	    List<DoctorSchedule> listOfSchedules = new ArrayList<>();
	    List<LocalDate> addedDates = new ArrayList<>();
	    List<LocalDate> skippedPastDates = new ArrayList<>();
	    List<LocalDate> alreadyMarkedDates = new ArrayList<>();

		for (LocalDate unavailableDate : listOfUnavailabeDates) {

			// Validate date is not in the past
			if (unavailableDate.isBefore(today)) {
			    skippedPastDates.add(unavailableDate);
			    continue;
			}

			// Check if already marked unavailable
			if (!doctorScheduleRepository.existsByStaff_StaffIdAndUnavailableDate(doctorId, unavailableDate)) {

				DoctorSchedule schedule = DoctorSchedule.builder()
				        .staff(staff)
				        .unavailableDate(unavailableDate)
						.build();

				listOfSchedules.add(schedule);
				addedDates.add(unavailableDate);
			} else {
			    alreadyMarkedDates.add(unavailableDate);
			}
		}

		if (listOfSchedules.isEmpty()) {
		    String message = "No new unavailable dates were added.";
		    if (!skippedPastDates.isEmpty()) {
		        message += " Past dates were skipped.";
		    }
		    if (!alreadyMarkedDates.isEmpty()) {
		        message += " Some dates were already marked unavailable.";
		    }
			throw new DoctorUnavailableException(message);
		}

		doctorScheduleRepository.saveAll(listOfSchedules);

		return addedDates;

	}

	@Override
	public List<LocalDate> getDoctorUnavailableDates(String doctorId) {
		// Validate staff exists and is a doctor
		Staff staff = staffRepository.findById(doctorId)
				.orElseThrow(() -> new StaffNotFoundException("Staff with ID " + doctorId + " not found"));

		if (staff.getStaffType() != com.flmhospitals.enums.StaffType.DOCTOR) {
			throw new IllegalArgumentException("Staff with ID " + doctorId + " is not a doctor");
		}

		// Get all unavailable dates for this doctor
		List<DoctorSchedule> schedules = doctorScheduleRepository.findByStaff_StaffId(doctorId);
		
		// Filter out past dates and return only future/today dates
		LocalDate today = LocalDate.now();
		return schedules.stream()
				.map(DoctorSchedule::getUnavailableDate)
				.filter(date -> !date.isBefore(today))
				.sorted()
				.toList();
	}

}
