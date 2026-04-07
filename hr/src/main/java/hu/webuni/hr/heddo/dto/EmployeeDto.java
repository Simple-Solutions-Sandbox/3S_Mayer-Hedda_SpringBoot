package hu.webuni.hr.heddo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Position must not be blank")
    private String position;
    @Positive(message = "Salary must be positive")
    private Integer salary;
    @Past(message = "Start date must be in the past")
    private LocalDateTime startDate;


    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, String position, Integer salary, LocalDateTime startDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
