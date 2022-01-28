package io.timesheet;

import io.timesheet.model.Employee;
import io.timesheet.model.Project;
import io.timesheet.model.Timesheet;
import io.timesheet.repository.EmployeeRepository;
import io.timesheet.repository.ProjectRepository;
import io.timesheet.repository.TimesheetRepository;
import io.timesheet.service.ProjectService;
import io.timesheet.service.TimesheetService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    TimesheetService timesheetService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    ProjectService projectService;

    @PostMapping("/submitEmployee")
    public @ResponseBody String submitEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return "Employee added to database";
    }

    @PostMapping("/submitProject")
    public @ResponseBody String submitProject(@RequestBody Project project) {
        projectRepository.save(project);
        return "Project added to database";
    }

    @PostMapping("/submitTimesheet")
    public @ResponseBody Map<String, Object> submitTimesheet(@RequestBody String strParam) {
        JSONObject request = new JSONObject(strParam);

        String employeeId = request.getString("employeeId");
        String projectId = request.getString("projectType");

        //Add timesheet data to database
        String projectName = request.getString("projectName");
        String workCode = request.getString("workCode");
        String startDate = request.getString("startDate");
        String endDate = request.getString("endDate");
        String timesheetStr = request.getString("timesheet");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Timesheet timesheet = new Timesheet();
        timesheet.setStartDate(LocalDate.parse(startDate, formatter));
        timesheet.setEndDate(LocalDate.parse(endDate, formatter));
        timesheet.setData(timesheetStr);
        timesheet.setProjectName(projectName);
        timesheet.setWorkCode(workCode);

        timesheetRepository.save(timesheet);
        timesheetService.employeeTimesheet(request, timesheet);

        Employee employee = employeeRepository.findById(Integer.valueOf(employeeId)).get();
        Project projects = projectRepository.findById(Integer.valueOf(projectId)).get();
        Timesheet timesheets = timesheetRepository.findById(Integer.valueOf(timesheet.getTimesheetId())).get();

        //linked employee with project
        projectService.employeeProject(request);

        //output
        JSONObject returnMap = TimesheetService.getTimesheetData(timesheetStr);
        returnMap.put("employeeNumber", employee.getEmployeeId());
        returnMap.put("employeeName", employee.getFirstName()+" "+employee.getLastName());
        returnMap.put("projectType", projects.getProjectId());
        returnMap.put("projectName", timesheets.getProjectName());
        returnMap.put("workCode", timesheets.getWorkCode());

        System.out.println(returnMap.toString());

        return returnMap.toMap();
    }
}
