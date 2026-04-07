package hu.webuni.hr.heddo.service;

import hu.webuni.hr.heddo.model.Employee;

import java.util.List;

public interface EmployeeService {

    int getPayRaisePercent(Employee employee);

    List<Employee> findAll();
    Employee findById(Long id);
    Employee save(Employee employee);
    void delete(Long id);
}
