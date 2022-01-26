package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "holidays")
public class Holidays {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer leaveId;
    private String leaveType;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate leaveDate;

    @JsonFormat(pattern="HH:mm")
    private LocalTime startLeaveHours;
    @JsonFormat(pattern="HH:mm")
    private LocalTime endLeaveHours;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    public Employee employee;

    public Holidays() {

    }
    public Holidays(Integer leaveId, String leaveType, LocalDate leaveDate,
                    LocalTime startLeaveHours, LocalTime endLeaveHours) {
        this.leaveType = leaveType;
        this.leaveDate = leaveDate;
        this.startLeaveHours = startLeaveHours;
        this.endLeaveHours = endLeaveHours;
        this.leaveId = leaveId;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public LocalTime getStartLeaveHours() {
        return startLeaveHours;
    }

    public void setStartLeaveHours(LocalTime startLeaveHours) {
        this.startLeaveHours = startLeaveHours;
    }

    public LocalTime getEndLeaveHours() {
        return endLeaveHours;
    }

    public void setEndLeaveHours(LocalTime endLeaveHours) {
        this.endLeaveHours = endLeaveHours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
