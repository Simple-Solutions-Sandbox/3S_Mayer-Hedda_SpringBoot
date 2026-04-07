package hu.webuni.hr.heddo.service;

import hu.webuni.hr.heddo.config.HrConfigProperties;
import hu.webuni.hr.heddo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!smart")
public class DefaultEmployeeService extends AbstractEmployeeService {

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        return config.getSalary().getDef().getPercent();
    }
}
