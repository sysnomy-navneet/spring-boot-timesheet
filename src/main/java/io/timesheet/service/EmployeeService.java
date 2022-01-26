package io.timesheet.service;

import io.timesheet.model.Employee;
import io.timesheet.repository.EmployeeRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public void manuallyCreateEmployee(JSONObject request) {

        System.out.println("employeeId "+Integer.valueOf(request.getString("employeeId")));
        Employee employee = new Employee();
        employee.setEmployeeId(Integer.valueOf(request.getString("employeeId")));
        employee.setDepartment("HR");
        employee.setFirstName("Customer");
        employee.setLastName("Singh");
        employeeRepository.save(employee);
    }
}
