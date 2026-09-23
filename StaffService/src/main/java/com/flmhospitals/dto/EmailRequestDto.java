package com.flmhospitals.dto;

import java.time.LocalDate;

import com.flmhospitals.enums.Specialization;
import com.flmhospitals.enums.StaffType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {

    private String to;
    private String subject;
    private String body;

    
}