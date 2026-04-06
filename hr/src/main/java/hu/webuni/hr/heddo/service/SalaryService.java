package hu.webuni.hr.heddo.service;

import hu.webuni.hr.heddo.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private final EmployeeService employeeService;

    public SalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setNewSalary(Employee employee) {
        int percent = employeeService.getPayRaisePercent(employee);
        int newSalary = employee.getSalary() + (employee.getSalary() * percent / 100);
        employee.setSalary(newSalary);
    }

}
