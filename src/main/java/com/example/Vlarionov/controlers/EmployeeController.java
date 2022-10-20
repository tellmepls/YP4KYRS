package com.example.Vlarionov.controlers;

import com.example.Vlarionov.Models.Employee;
import com.example.Vlarionov.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
    public String EmployeeAddView(Employee employee) {return ("employee/employeeADD");}

    @PostMapping("/employee/add")
    public String EmployeeAdd(@Valid Employee employee,
                              BindingResult result) {
        if(result.hasErrors())
            return ("employee/employeeADD");

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
    @GetMapping("/employee/details/{id}")
    public String EmployeeDetails(Model model,
                                  @PathVariable long id) {

        Optional<Employee> car = employeeRepository.findById(id);
        ArrayList<Employee> result = new ArrayList<>();

        car.ifPresent(result::add);
        model.addAttribute("employee", result);
        return ("/employee/employeeDetails");
    }

    @GetMapping("employee/delete/{id}")
    public String EmployeeDelete(@PathVariable long id) {

        employeeRepository.deleteById(id);
        return("redirect:/employee");
    }

    @GetMapping("employee/employeeEdit/{id}")
    public String EmployeeEdit(Model model,
                          @PathVariable long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", emp);
        return("/employee/employeeEdit");
    }

    @PostMapping("employee/employeeEdit/{id}")
    public String employeeEdit(@Valid Employee employee,
                               BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())
            return("/employee/employeeEdit");

        employeeRepository.save(employee);

        return("redirect:/employee/details/" + employee.getId());
    }
}
