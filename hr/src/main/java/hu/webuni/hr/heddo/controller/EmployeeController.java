package hu.webuni.hr.heddo.controller;

import hu.webuni.hr.heddo.dto.EmployeeDto;
import hu.webuni.hr.heddo.mapper.EmployeeMapper;
import hu.webuni.hr.heddo.model.Employee;
import hu.webuni.hr.heddo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

//    private Map<Long, EmployeeDto> employees = new HashMap<>();
//    {
//        employees.put(1L, new EmployeeDto(1L, "John Doe", "Software Engineer", 60000, java.time.LocalDateTime.now().minusYears(7)));
//        employees.put(2L, new EmployeeDto(2L, "Jane Smith", "Project Manager", 75000, java.time.LocalDateTime.now().minusYears(12)));
//        employees.put(3L, new EmployeeDto(3L, "Alice Johnson", "QA Engineer", 55000, java.time.LocalDateTime.now().minusYears(3)));
//    }

    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
//        List<EmployeeDto> allEmployees = new ArrayList<>(employees.values());
//
//        if (minSalary == null) {
//            return allEmployees;
//        }
//
//        return allEmployees.stream()
//                .filter(e -> e.getSalary() != null && e.getSalary() >= minSalary)
//                .collect(java.util.stream.Collectors.toList());
        List<Employee> allEmployees = employeeService.findAll();
        if (minSalary == null) {
            return employeeMapper.employeesToEmployeeDtos(allEmployees);
        }

        return employeeMapper.employeesToEmployeeDtos(allEmployees.stream()
                .filter(e -> e.getSalary() != null && e.getSalary() >= minSalary)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
//        EmployeeDto employeeDto = employees.get(id);
//        if (employeeDto != null) {
//            return ResponseEntity.ok(employeeDto);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            return ResponseEntity.ok(employeeMapper.employeeToEmployeeDto(employee));
        } else {
            return ResponseEntity.notFound().build();
            }
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
//        employees.put(employeeDto.getId(), employeeDto);
//        return employeeDto;
        Employee employee = employeeService.save(employeeMapper.employeeDtoToEmployee(employeeDto));
        return employeeMapper.employeeToEmployeeDto(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) {
//        if (employees.containsKey(id)) {
//            employeeDto.setId(id);
//            employees.put(id, employeeDto);
//            return ResponseEntity.ok(employeeDto);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        if (employeeService.findById(id) != null) {
            employeeDto.setId(id);
            Employee updatedEmployee = employeeService.save(employeeMapper.employeeDtoToEmployee(employeeDto));
            return ResponseEntity.ok(employeeMapper.employeeToEmployeeDto(updatedEmployee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
//        employees.remove(id);
        employeeService.delete(id);
    }

}
