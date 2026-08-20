package com.employeecreator.dto;

import com.employeecreator.entity.ContractType;
import com.employeecreator.entity.EmploymentType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeRequestDTO {

  @NotBlank(message = "First name is required")
  @Size(max = 50, message = "First name must not exceed 50 characters")
  private String firstName;

  @Size(max = 50, message = "Middle name must not exceed 50 characters")
  private String middleName;

  @NotBlank(message = "Last name is required")
  @Size(max = 50, message = "Last name must not exceed 50 characters")
  private String lastName;

  @NotBlank(message = "Email is required")
  @Email(message = "Please provide a valid email address")
  @Size(max = 100, message = "Email must not exceed 100 characters")
  private String email;

  @NotBlank(message = "Mobile number is required")
  @Pattern(regexp = "^04\\d{8}$", message = "Mobile must be a valid Australian mobile number")
  private String mobile;

  @NotBlank(message = "Residential address is required")
  @Size(max = 255, message = "Address must not exceed 255 characters")
  private String residentialAddress;

  @NotNull(message = "Contract type is required")
  private ContractType contractType;

  @NotNull(message = "Start date is required")
  private LocalDate startDate;

  private LocalDate finishedDate;

  private boolean ongoing;

  @NotNull(message = "Employment type is required")
  private EmploymentType employmentType;

  @NotNull(message = "Hours per week is required")
  @Min(value = 1, message = "Hours per week must be at least 1")
  @Max(value = 168, message = "Hours per week cannot exceed 168")
  private Integer hoursPerWeek;

  public EmployeeRequestDTO() {
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

  // Generate getters and setters
}
