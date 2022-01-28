package io.timesheet.service;

import io.timesheet.model.Employee;
import io.timesheet.model.Project;
import io.timesheet.repository.EmployeeRepository;
import io.timesheet.repository.ProjectRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public void employeeProject(JSONObject request) {
        String employeeId = request.getString("employeeId");
        String projectId = request.getString("projectType");

        // many employee to many project
        if(employeeRepository.getById(Integer.valueOf(employeeId)).getEmployeeId() != null && projectRepository.getById(Integer.valueOf(projectId)).getProjectId() != null) {
            Employee employee = employeeRepository.findById(Integer.valueOf(employeeId)).get();
            Project projects = projectRepository.findById(Integer.valueOf(projectId)).get();

            employee.enrolledProject(projects);
            employeeRepository.save(employee);
        }
    }

}
