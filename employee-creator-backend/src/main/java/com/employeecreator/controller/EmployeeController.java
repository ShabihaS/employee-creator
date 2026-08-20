package com.employeecreator.controller;

import com.employeecreator.dto.EmployeeRequestDTO;
import com.employeecreator.dto.EmployeeResponseDTO;
import com.employeecreator.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping
  public ResponseEntity<EmployeeResponseDTO> createEmployee(
      @Valid @RequestBody EmployeeRequestDTO request) {

    EmployeeResponseDTO employee = employeeService.createEmployee(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(employee);
  }

  @GetMapping
  public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {

    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
      @PathVariable Long id) {

    return ResponseEntity.ok(employeeService.getEmployeeById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> updateEmployee(
      @PathVariable Long id,
      @Valid @RequestBody EmployeeRequestDTO request) {

    return ResponseEntity.ok(
        employeeService.updateEmployee(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(
      @PathVariable Long id) {

    employeeService.deleteEmployee(id);

    return ResponseEntity.noContent().build();
  }
}
