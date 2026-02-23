package com.infinira.ems.controller;

import com.infinira.ems.model.Department;
import com.infinira.ems.model.Employee;
import com.infinira.ems.service.EmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmscontrollerTest {

    @Mock
    private EmsService emsService;

    @InjectMocks
    private EmsController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // --- Employee tests ---
    @Test
    @DisplayName("Should create employee and return CREATED with generated ID")
    void createEmployee_returnsCreatedAndId() {
        Employee input = new Employee();
        when(emsService.createEmployee(any(Employee.class))).thenReturn(101);

        ResponseEntity<Integer> response = controller.createEmployee(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(101);
    }

    @Test
    @DisplayName("Should update employee and return OK with update count")
    void updateEmployee_returnsOkAndUpdateCount() {
        Employee update = new Employee();
        when(emsService.updateEmployee(eq(5), any(Employee.class))).thenReturn(1);

        ResponseEntity<Integer> response = controller.updateEmployee(5, update);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should get employee by id and return OK with employee body")
    void getEmployeeById_returnsOkAndEmployee() {
        Employee e = new Employee();
        when(emsService.getEmployeeById(7)).thenReturn(e);

        ResponseEntity<Employee> response = controller.getEmployeeById(7);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(e);
    }

    @Test
    @DisplayName("Should return OK with list of all employees")
    void getAllEmployees_returnsOkAndList() {
        List<Employee> list = Arrays.asList(new Employee(), new Employee());
        when(emsService.getAllEmployees()).thenReturn(list);

        ResponseEntity<List<Employee>> response = controller.getAllEmployees();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactlyElementsOf(list);
    }

    @Test
    @DisplayName("Should delete employee and return OK with delete count")
    void deleteEmployee_returnsOkAndDeleteCount() {
        when(emsService.deleteEmployee(9)).thenReturn(1);

        ResponseEntity<Integer> response = controller.deleteEmployee(9);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(1);
    }

    // --- Department tests ---
    @Test
    @DisplayName("Should create department and return CREATED with generated ID")
    void createDepartment_returnsCreatedAndId() {
        Department d = new Department();
        when(emsService.createDepartment(any(Department.class))).thenReturn(201);

        ResponseEntity<Integer> response = controller.createDepartment(d);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(201);
    }

    @Test
    @DisplayName("Should update department and return OK with update count")
    void updateDepartment_returnsOkAndUpdateCount() {
        Department d = new Department();
        when(emsService.updateDepartment(eq(3), any(Department.class))).thenReturn(1);

        ResponseEntity<Integer> response = controller.updateDepartment(3, d);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should get department by id and return OK with body")
    void getDepartmentById_returnsOkAndBody() {
        Department d = new Department();
        when(emsService.getDepartmentById(4)).thenReturn(d);

        ResponseEntity<Department> response = controller.getDepartmentById(4);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(d);
    }

    @Test
    @DisplayName("Should return OK with list of all departments (empty allowed)")
    void getAllDepartments_returnsOkAndList() {
        List<Department> list = Collections.emptyList();
        when(emsService.getAllDepartments()).thenReturn(list);

        ResponseEntity<List<Department>> response = controller.getAllDepartments();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    @DisplayName("Should delete department and return OK with delete count")
    void deleteDepartment_returnsOkAndDeleteCount() {
        when(emsService.deleteDepartment(11)).thenReturn(1);

        ResponseEntity<Integer> response = controller.deleteDepartment(11);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(1);
    }
}
