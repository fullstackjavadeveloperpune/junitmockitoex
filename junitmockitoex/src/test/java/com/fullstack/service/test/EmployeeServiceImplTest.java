package com.fullstack.service.test;

import com.fullstack.model.Employee;
import com.fullstack.repository.EmployeeRepository;
import com.fullstack.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @MockitoBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTest(){
        Employee employee = new Employee(121, "SWARA", 97000);
        employeeService.save(employee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    public void findAllTest(){
        Mockito.when(employeeRepository.findAll()).thenReturn(Stream.of(new Employee(121, "SWARA", 97000),
                new Employee(122, "KIRAN", 99000),
                new Employee(123, "RAM", 91000)).toList());

        Assertions.assertEquals(3, employeeService.findAll().size());
    }

    @Test
    public void findByIdTest(){
        Mockito.when(employeeRepository.findById(121)).thenReturn(Optional.of(new Employee(121, "SWARA", 97000)));
        employeeService.findById(121);
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(121);
    }

    @Test
    public void updateTest(){
        Employee employee = new Employee(121, "SWARA", 97000);
        employeeService.update(employee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }
    @Test
    public void deleteByIdTest(){
        Employee employee = new Employee(121, "SWARA", 97000);
        employeeService.deleteById(employee.getEmpId());
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(employee.getEmpId());
    }

}
