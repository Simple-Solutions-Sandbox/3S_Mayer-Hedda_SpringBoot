package hu.webuni.hr.heddo.service;

import hu.webuni.hr.heddo.config.HrConfigProperties;
import hu.webuni.hr.heddo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Profile("smart")
public class SmartEmployeeService extends AbstractEmployeeService {

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        long yearsAtCompany = ChronoUnit.YEARS.between(employee.getStartDate(), LocalDateTime.now());
        if (yearsAtCompany >= config.getSalary().getSmart().getLimit1()) {
            return config.getSalary().getSmart().getPercent1();
        } else if (yearsAtCompany >= config.getSalary().getSmart().getLimit2()) {
            return config.getSalary().getSmart().getPercent2();
        } else {
            long monthsAtCompany = ChronoUnit.MONTHS.between(employee.getStartDate(), LocalDateTime.now());

            if (monthsAtCompany >= config.getSalary().getSmart().getLimit3() * 12) {
                return config.getSalary().getSmart().getPercent3();
            } else {
                return config.getSalary().getSmart().getDefaultPercent();
            }
        }
    }
}
