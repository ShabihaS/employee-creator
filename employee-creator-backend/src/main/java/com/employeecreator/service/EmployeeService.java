package com.employeecreator.service;

import com.employeecreator.dto.EmployeeRequestDTO;
import com.employeecreator.dto.EmployeeResponseDTO;
import com.employeecreator.entity.Employee;
import com.employeecreator.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.employeecreator.common.exceptions.NotFoundException;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {

    Employee employee = new Employee();

    employee.setFirstName(request.getFirstName());
    employee.setMiddleName(request.getMiddleName());
    employee.setLastName(request.getLastName());
    employee.setEmail(request.getEmail());
    employee.setMobile(request.getMobile());
    employee.setResidentialAddress(request.getResidentialAddress());
    employee.setContractType(request.getContractType());
    employee.setStartDate(request.getStartDate());
    employee.setFinishedDate(request.getFinishedDate());
    employee.setOngoing(request.isOngoing());
    employee.setEmploymentType(request.getEmploymentType());
    employee.setHoursPerWeek(request.getHoursPerWeek());

    Employee savedEmployee = employeeRepository.save(employee);

    return convertToResponseDTO(savedEmployee);
  }

  // get All employee

  public List<EmployeeResponseDTO> getAllEmployees() {

    return employeeRepository.findAll()
        .stream()
        .map(this::convertToResponseDTO)
        .toList();
  }

  // getEmployeeById

  public EmployeeResponseDTO getEmployeeById(Long id) {

    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));

    return convertToResponseDTO(employee);
  }

  // Update Employee

  public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request) {

    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));

    employee.setFirstName(request.getFirstName());
    employee.setMiddleName(request.getMiddleName());
    employee.setLastName(request.getLastName());
    employee.setEmail(request.getEmail());
    employee.setMobile(request.getMobile());
    employee.setResidentialAddress(request.getResidentialAddress());
    employee.setContractType(request.getContractType());
    employee.setStartDate(request.getStartDate());
    employee.setFinishedDate(request.getFinishedDate());
    employee.setOngoing(request.isOngoing());
    employee.setEmploymentType(request.getEmploymentType());
    employee.setHoursPerWeek(request.getHoursPerWeek());

    Employee updatedEmployee = employeeRepository.save(employee);

    return convertToResponseDTO(updatedEmployee);
  }

  // Delete
  public void deleteEmployee(Long id) {

    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));

    employeeRepository.delete(employee);
  }

  private EmployeeResponseDTO convertToResponseDTO(Employee employee) {

    EmployeeResponseDTO response = new EmployeeResponseDTO();

    response.setId(employee.getId());
    response.setFirstName(employee.getFirstName());
    response.setMiddleName(employee.getMiddleName());
    response.setLastName(employee.getLastName());
    response.setEmail(employee.getEmail());
    response.setMobile(employee.getMobile());
    response.setResidentialAddress(employee.getResidentialAddress());
    response.setContractType(employee.getContractType());
    response.setStartDate(employee.getStartDate());
    response.setFinishedDate(employee.getFinishedDate());
    response.setOngoing(employee.isOngoing());
    response.setEmploymentType(employee.getEmploymentType());
    response.setHoursPerWeek(employee.getHoursPerWeek());

    return response;
  }
}