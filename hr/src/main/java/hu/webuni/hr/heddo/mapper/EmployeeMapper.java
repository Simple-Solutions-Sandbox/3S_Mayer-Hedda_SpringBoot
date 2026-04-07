package hu.webuni.hr.heddo.mapper;

import hu.webuni.hr.heddo.dto.EmployeeDto;
import hu.webuni.hr.heddo.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeToEmployeeDto(Employee employee);
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> employeesToEmployeeDtos(List<Employee> employees);

}
