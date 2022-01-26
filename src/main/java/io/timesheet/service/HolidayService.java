package io.timesheet.service;

import io.timesheet.model.Employee;
import io.timesheet.model.Holidays;
import io.timesheet.repository.EmployeeRepository;
import io.timesheet.repository.HolidaysRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class HolidayService {

    @Autowired
    HolidaysRepository holidaysRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public void manuallySubmitHoliday(JSONObject request) {
        Holidays holidays = new Holidays();
        holidays.setLeaveId(Integer.valueOf("1"));
        holidays.setLeaveType("Outstation duty");
        holidays.setLeaveDate(LocalDate.now());
        holidays.setStartLeaveHours(LocalTime.now());
        holidays.setEndLeaveHours(LocalTime.now());
        holidaysRepository.save(holidays);

        employeeHoliday(request, holidays);

    }

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
