
package com.employeecreator.service;

import com.employeecreator.common.exceptions.NotFoundException;
import com.employeecreator.dto.EmployeeRequestDTO;
import com.employeecreator.dto.EmployeeResponseDTO;
import com.employeecreator.entity.ContractType;
import com.employeecreator.entity.Employee;
import com.employeecreator.entity.EmploymentType;
import com.employeecreator.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeService employeeService;

  private EmployeeRequestDTO request;
  private Employee employee;

  @BeforeEach
  void setUp() {
    request = new EmployeeRequestDTO();

    request.setFirstName("John");
    request.setMiddleName("David");
    request.setLastName("Smith");
    request.setEmail("john.smith@example.com");
    request.setMobile("0412345678");
    request.setResidentialAddress("10 George Street, Sydney NSW");
    request.setContractType(ContractType.PERMANENT);
    request.setStartDate(LocalDate.of(2026, 1, 1));
    request.setFinishedDate(null);
    request.setOngoing(true);
    request.setEmploymentType(EmploymentType.FULL_TIME);
    request.setHoursPerWeek(38);

    employee = new Employee();

    employee.setId(1L);
    employee.setFirstName("John");
    employee.setMiddleName("David");
    employee.setLastName("Smith");
    employee.setEmail("john.smith@example.com");
    employee.setMobile("0412345678");
    employee.setResidentialAddress("10 George Street, Sydney NSW");
    employee.setContractType(ContractType.PERMANENT);
    employee.setStartDate(LocalDate.of(2026, 1, 1));
    employee.setFinishedDate(null);
    employee.setOngoing(true);
    employee.setEmploymentType(EmploymentType.FULL_TIME);
    employee.setHoursPerWeek(38);
  }

  @Test
  void createEmployee_shouldCreateEmployeeSuccessfully() {

    when(employeeRepository.save(any(Employee.class)))
        .thenReturn(employee);

    EmployeeResponseDTO result = employeeService.createEmployee(request);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Smith", result.getLastName());
    assertEquals("john.smith@example.com", result.getEmail());
    assertEquals("0412345678", result.getMobile());

    verify(employeeRepository).save(any(Employee.class));
  }

  @Test
  void getEmployeeById_shouldReturnEmployee() {

    when(employeeRepository.findById(1L))
        .thenReturn(Optional.of(employee));

    EmployeeResponseDTO result = employeeService.getEmployeeById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Smith", result.getLastName());

    verify(employeeRepository).findById(1L);
  }

  @Test
  void getEmployeeById_shouldThrowNotFoundException() {

    when(employeeRepository.findById(999L))
        .thenReturn(Optional.empty());

    assertThrows(
        NotFoundException.class,
        () -> employeeService.getEmployeeById(999L));

    verify(employeeRepository).findById(999L);
  }

  @Test
  void getAllEmployees_shouldReturnEmployees() {

    Employee secondEmployee = new Employee();

    secondEmployee.setId(2L);
    secondEmployee.setFirstName("Sarah");
    secondEmployee.setLastName("Wilson");
    secondEmployee.setEmail("sarah.wilson@example.com");
    secondEmployee.setMobile("0498765432");
    secondEmployee.setResidentialAddress("20 Pitt Street, Sydney NSW");
    secondEmployee.setContractType(ContractType.CONTRACT);
    secondEmployee.setStartDate(LocalDate.of(2026, 2, 1));
    secondEmployee.setFinishedDate(LocalDate.of(2028, 2, 1));
    secondEmployee.setOngoing(false);
    secondEmployee.setEmploymentType(EmploymentType.PART_TIME);
    secondEmployee.setHoursPerWeek(30);

    when(employeeRepository.findAll())
        .thenReturn(List.of(employee, secondEmployee));

    List<EmployeeResponseDTO> result = employeeService.getAllEmployees();

    assertEquals(2, result.size());
    assertEquals("John", result.get(0).getFirstName());
    assertEquals("Sarah", result.get(1).getFirstName());

    verify(employeeRepository).findAll();
  }

  @Test
  void updateEmployee_shouldUpdateEmployeeSuccessfully() {

    when(employeeRepository.findById(1L))
        .thenReturn(Optional.of(employee));

    when(employeeRepository.save(any(Employee.class)))
        .thenReturn(employee);

    request.setFirstName("Jonathan");
    request.setLastName("Smithson");
    request.setEmail("jonathan.smithson@example.com");

    EmployeeResponseDTO result = employeeService.updateEmployee(1L, request);

    assertNotNull(result);
    assertEquals("Jonathan", result.getFirstName());
    assertEquals("Smithson", result.getLastName());
    assertEquals(
        "jonathan.smithson@example.com",
        result.getEmail());

    verify(employeeRepository).findById(1L);
    verify(employeeRepository).save(employee);
  }

  @Test
  void updateEmployee_shouldThrowNotFoundException() {

    when(employeeRepository.findById(999L))
        .thenReturn(Optional.empty());

    assertThrows(
        NotFoundException.class,
        () -> employeeService.updateEmployee(999L, request));

    verify(employeeRepository).findById(999L);
    verify(employeeRepository, never()).save(any(Employee.class));
  }

  @Test
  void deleteEmployee_shouldDeleteEmployeeSuccessfully() {

    when(employeeRepository.findById(1L))
        .thenReturn(Optional.of(employee));

    employeeService.deleteEmployee(1L);

    verify(employeeRepository).findById(1L);
    verify(employeeRepository).delete(employee);
  }

  @Test
  void deleteEmployee_shouldThrowNotFoundException() {

    when(employeeRepository.findById(999L))
        .thenReturn(Optional.empty());

    assertThrows(
        NotFoundException.class,
        () -> employeeService.deleteEmployee(999L));

    verify(employeeRepository).findById(999L);
    verify(employeeRepository, never()).delete(any(Employee.class));
  }
}
