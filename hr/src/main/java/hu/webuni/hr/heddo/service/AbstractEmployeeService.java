package hu.webuni.hr.heddo.service;

import hu.webuni.hr.heddo.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractEmployeeService implements EmployeeService {

    protected Map<Long, Employee> employees = new HashMap<>();

    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    public Employee findById(Long id) {
        return employees.get(id);
    }

    public Employee save(Employee employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    public void delete(Long id) {
        employees.remove(id);
    }

}
