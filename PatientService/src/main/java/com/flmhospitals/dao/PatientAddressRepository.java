package com.flmhospitals.dao;

import com.flmhospitals.model.PatientAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAddressRepository extends JpaRepository<PatientAddress, Long> {

}
