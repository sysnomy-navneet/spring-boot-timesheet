package io.timesheet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {

    @Id
    private Integer projectId;
    private String projectName;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate endDate;
    private String status;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledProjects")
    private Set<Employee> employees = new HashSet<>();
}
