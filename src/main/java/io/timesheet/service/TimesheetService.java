package io.timesheet.service;

import io.timesheet.model.Employee;
import io.timesheet.model.Project;
import io.timesheet.model.Timesheet;
import io.timesheet.repository.EmployeeRepository;
import io.timesheet.repository.TimesheetRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;

@Service
public class TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public void manuallyCreateTimesheet(JSONObject request) {
        Timesheet timesheet = new Timesheet();
        timesheet.setTimesheetId(Integer.valueOf("1"));
        timesheet.setStartDate(LocalDate.now());
        timesheet.setEndDate(LocalDate.now());
        timesheet.setData("Timesheet Data");
        timesheetRepository.save(timesheet);

        employeeTimesheet(request, timesheet);
    }

    public void employeeTimesheet(JSONObject request, Timesheet timesheet) {
        String employeeId = request.getString("employeeId");

        if(timesheetRepository.getById(Integer.valueOf(timesheet.getTimesheetId())) != null) {
            // One employee to many timesheet
            Employee employees = employeeRepository.findById(Integer.valueOf(employeeId)).get();
            Timesheet timesheets = timesheetRepository.findById(Integer.valueOf(timesheet.getTimesheetId())).get();

            timesheets.setEmployee(employees);
            timesheetRepository.save(timesheets);
        }
    }

    public static JSONObject getTimesheetData(String timesheetST) {
        JSONObject time = new JSONObject(timesheetST);
        int attendanceST = 0;
        int attendanceAPH = 0;
        int overheadST = 0;
        int overheadAPH = 0;

        JSONArray timesheetArr = new JSONArray();
        Iterator<String> iterator = time.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            JSONObject value = time.getJSONObject(key);

            if(value.getString("ST") != null) attendanceST += Integer.valueOf(value.getString("ST"));
            if(value.getString("APH") != null) attendanceAPH += Integer.valueOf(value.getString("APH"));

            JSONObject timesheetMap = new JSONObject();
            timesheetMap.put("day", key);
            if(value.getString("ST") != null) timesheetMap.put("projectST", value.getString("ST"));
            if(value.getString("APH") != null) timesheetMap.put("projectAPH", value.getString("APH"));
            timesheetMap.put("projectDate", value.getString("Date"));

            if(value.get("OVERHEAD") instanceof JSONObject) {
                JSONObject overhead = value.getJSONObject("OVERHEAD");
                if(overhead.getString("ST") != null) overheadST += Integer.valueOf(overhead.getString("ST"));
                if(overhead.getString("APH") != null) overheadAPH += Integer.valueOf(overhead.getString("APH"));

                if(overhead.getString("ST") != null) timesheetMap.put("overheadST", overhead.getString("ST"));
                if(overhead.getString("APH") != null) timesheetMap.put("overheadAPH", overhead.getString("APH"));
            }

            timesheetArr.put(timesheetMap);
        }

        JSONObject returnMap = new JSONObject();
        returnMap.put("timesheet", timesheetArr.toString());
        returnMap.put("totalST", attendanceST);
        returnMap.put("totalAPH", attendanceAPH);
        returnMap.put("totalOverheadST", overheadST);
        returnMap.put("totalOverheadAPH", overheadAPH);

        return returnMap;
    }
}
