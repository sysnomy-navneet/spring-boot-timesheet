package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id
    private Integer projectId;
    private String projectName;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate endDate;

    private String status;

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledProjects")
    private Set<Employee> employees = new HashSet<>();

    public Project() {

    }
    public Project(Integer projectId, String projectName, LocalDate startDate,
                   LocalDate endDate, String status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
}
