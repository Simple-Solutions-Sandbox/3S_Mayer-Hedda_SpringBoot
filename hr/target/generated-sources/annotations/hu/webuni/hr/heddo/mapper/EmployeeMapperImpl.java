package hu.webuni.hr.heddo.mapper;

import hu.webuni.hr.heddo.dto.EmployeeDto;
import hu.webuni.hr.heddo.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-08T02:42:28+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setName( employee.getName() );
        employeeDto.setPosition( employee.getPosition() );
        employeeDto.setSalary( employee.getSalary() );
        employeeDto.setStartDate( employee.getStartDate() );

        return employeeDto;
    }

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setName( employeeDto.getName() );
        employee.setPosition( employeeDto.getPosition() );
        employee.setSalary( employeeDto.getSalary() );
        employee.setStartDate( employeeDto.getStartDate() );

        return employee;
    }

    @Override
    public List<EmployeeDto> employeesToEmployeeDtos(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( employeeToEmployeeDto( employee ) );
        }

        return list;
    }
}
