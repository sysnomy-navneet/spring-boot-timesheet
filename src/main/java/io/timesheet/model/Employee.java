package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String department;

    @ManyToMany
    @JoinTable(
            name = "project_enrolled",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Project> enrolledProjects = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Timesheet> timesheets = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Holidays> holidays = new HashSet<>();

    public Employee() {

    }

    public Employee(Integer employeeId, String firstName, String lastName, String department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public Set<Project> getEnrolledProjects() {
        return enrolledProjects;
    }

    public void enrolledProject(Project project) {
        enrolledProjects.add(project);
    }

    public Set<Timesheet> getTimesheets() {
        return timesheets;
    }

    public Set<Holidays> getHolidays() {
        return holidays;
    }
}
