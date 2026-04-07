package hu.webuni.hr.heddo.controller;

import hu.webuni.hr.heddo.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private Map<Long, EmployeeDto> employees = new HashMap<>();
    {
        employees.put(1L, new EmployeeDto(1L, "John Doe", "Software Engineer", 60000, java.time.LocalDateTime.now().minusYears(7)));
        employees.put(2L, new EmployeeDto(2L, "Jane Smith", "Project Manager", 75000, java.time.LocalDateTime.now().minusYears(12)));
        employees.put(3L, new EmployeeDto(3L, "Alice Johnson", "QA Engineer", 55000, java.time.LocalDateTime.now().minusYears(3)));
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeDto = employees.get(id);
        if (employeeDto != null) {
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        employees.put(employeeDto.getId(), employeeDto);
        return employeeDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        if (employees.containsKey(id)) {
            employeeDto.setId(id);
            employees.put(id, employeeDto);
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employees.remove(id);
    }

    @GetMapping("/by-salary")
    public List<EmployeeDto> getEmployeesBySalary(@RequestParam(required = false) Integer minSalary) {
        List<EmployeeDto> allEmployees = new ArrayList<>(employees.values());

        if (minSalary == null) {
            return allEmployees;
        }

        return allEmployees.stream()
                .filter(e -> e.getSalary() != null && e.getSalary() >= minSalary)
                .collect(java.util.stream.Collectors.toList());
    }
}
