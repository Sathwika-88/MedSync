package com.flmhospitals.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flmhospitals.model.BedAssignmentHistory;

public interface BedAssignmentHistoryRepository extends JpaRepository<BedAssignmentHistory, Long>{

	BedAssignmentHistory findTopByBed_BedNumberAndVacatedAtIsNullOrderByAssignedAtDesc(long bedNumber);
	
	List<BedAssignmentHistory> findByBed_BedNumber(long bedNumber);
}
