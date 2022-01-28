package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    public void enrolledProject(Project project) {
        enrolledProjects.add(project);
    }

}
