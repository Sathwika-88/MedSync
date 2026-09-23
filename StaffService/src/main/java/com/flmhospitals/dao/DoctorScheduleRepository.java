package com.flmhospitals.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flmhospitals.model.DoctorSchedule;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
	
	List<DoctorSchedule> findByStaff_StaffId(String staffId);

	boolean existsByStaff_StaffIdAndUnavailableDate(String staffId, LocalDate unavailableDate);
	
	Optional<DoctorSchedule> findByStaff_StaffIdAndUnavailableDate(String staffId, LocalDate unavailableDate);

}
