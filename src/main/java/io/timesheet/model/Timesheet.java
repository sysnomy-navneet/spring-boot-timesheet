package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "timesheet")
public class Timesheet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer timesheetId;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate endDate;

    private String projectName;
    private String workCode;

    @Lob
    @Column
    private String data;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    public Employee employee;

    public Timesheet() {

    }
    public Timesheet(Integer timesheetId, LocalDate startDate,
                     LocalDate endDate, String data,
                     String projectName, String workCode) {
        this.timesheetId = timesheetId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.data = data;
        this.projectName = projectName;
        this.workCode = workCode;
    }

    public void setTimesheetId(Integer timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Integer getTimesheetId() {
        return timesheetId;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }
}
