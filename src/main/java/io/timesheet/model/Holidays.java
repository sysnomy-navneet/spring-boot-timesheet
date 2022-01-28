package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "holidays")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
