package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "timesheet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
}
