package com.employeecreator.dto;

import com.employeecreator.entity.ContractType;
import com.employeecreator.entity.EmploymentType;

import java.time.LocalDate;

public class EmployeeResponseDTO {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String mobile;

    private String residentialAddress;

    private ContractType contractType;

    private LocalDate startDate;

    private LocalDate finishedDate;

    private boolean ongoing;

    private EmploymentType employmentType;

    private Integer hoursPerWeek;

    public EmployeeResponseDTO() {
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getMiddleName() {
      return middleName;
    }

    public void setMiddleName(String middleName) {
      this.middleName = middleName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getMobile() {
      return mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getResidentialAddress() {
      return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
      this.residentialAddress = residentialAddress;
    }

    public ContractType getContractType() {
      return contractType;
    }

    public void setContractType(ContractType contractType) {
      this.contractType = contractType;
    }

    public LocalDate getStartDate() {
      return startDate;
    }

    public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
    }

    public LocalDate getFinishedDate() {
      return finishedDate;
    }

    public void setFinishedDate(LocalDate finishedDate) {
      this.finishedDate = finishedDate;
    }

    public boolean isOngoing() {
      return ongoing;
    }

    public void setOngoing(boolean ongoing) {
      this.ongoing = ongoing;
    }

    public EmploymentType getEmploymentType() {
      return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
      this.employmentType = employmentType;
    }

    public Integer getHoursPerWeek() {
      return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
      this.hoursPerWeek = hoursPerWeek;
    }

    // Getters and setters
    
}