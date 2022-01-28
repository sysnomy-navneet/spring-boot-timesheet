package io.timesheet.service;

import io.timesheet.model.Employee;
import io.timesheet.model.Holidays;
import io.timesheet.repository.EmployeeRepository;
import io.timesheet.repository.HolidaysRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {

    @Autowired
    HolidaysRepository holidaysRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private void employeeHoliday(JSONObject request, Holidays holidays) {
        String employeeId = request.getString("employeeId");

        // One employee to many holidays
        if(holidaysRepository.getById(Integer.valueOf(holidays.getLeaveId())) != null) {
            Employee employee = employeeRepository.findById(Integer.valueOf(employeeId)).get();
            Holidays holiday = holidaysRepository.findById(Integer.valueOf(holidays.getLeaveId())).get();

            holiday.setEmployee(employee);
            holidaysRepository.save(holiday);
        }
    }

}
