package hu.webuni.hr.heddo.controller;

import hu.webuni.hr.heddo.dto.EmployeeDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeTLController {

    private List<EmployeeDto> allEmployees = new ArrayList<>();
    {
        allEmployees.add(new EmployeeDto(1L, "John Doe", "Software Engineer", 60000, java.time.LocalDateTime.now().minusYears(7)));
        allEmployees.add(new EmployeeDto(2L, "Jane Smith", "Project Manager", 75000, java.time.LocalDateTime.now().minusYears(12)));
        allEmployees.add(new EmployeeDto(3L, "Alice Johnson", "QA Engineer", 55000, java.time.LocalDateTime.now().minusYears(3)));
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/employees")
    public String listEmployees(Map<String, Object> model) {
        model.put("employees", allEmployees);
        model.put("newEmployee", new EmployeeDto());
        return "employees";
    }

    @PostMapping("/employees")
    public String addEmployee(EmployeeDto employeeDto) {
        allEmployees.add(employeeDto);
        return "redirect:/employees";
    }

}
