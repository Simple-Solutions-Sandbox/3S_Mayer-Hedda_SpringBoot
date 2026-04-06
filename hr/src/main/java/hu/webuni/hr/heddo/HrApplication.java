package hu.webuni.hr.heddo;

import hu.webuni.hr.heddo.model.Employee;
import hu.webuni.hr.heddo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    private SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee(1L, "John Doe", "Developer", 50000, java.time.LocalDateTime.now().minusYears(12));
        System.out.println("Current salary: " + employee.getSalary());
        salaryService.setNewSalary(employee);
        System.out.println("New salary: " + employee.getSalary());
    }
}
