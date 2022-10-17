package com.example.Vlarionov.controlers;

import com.example.Vlarionov.Models.Employee;
import com.example.Vlarionov.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public String Employee(Model model) {

        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
        return ("employee/index");
    }

    @GetMapping("/employee/add")
    public String EmployeeAddView() {return ("employee/employeeADD");}

    @PostMapping("/employee/add")
    public String EmployeeAdd(@RequestParam String surname,
                              @RequestParam String name,
                              @RequestParam String middleName,
                              @RequestParam Integer passport,
                              @RequestParam String birthday) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Employee employee = new Employee(surname, name, middleName, passport, formatter.parse(birthday, new ParsePosition(0)));
        employeeRepository.save(employee);
        return ("redirect:/employee");
    }

    @GetMapping("/employee/filterACCU")
    public String EmployeeFilterACCU(Model model,
                                     @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurname(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }

    @GetMapping("/employee/filter")
    public String EmployeeFilter(Model model,
                                 @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurnameContains(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }
}
