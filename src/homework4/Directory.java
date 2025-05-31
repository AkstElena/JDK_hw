package homework4;

import java.util.*;

public class Directory {
    private List<Employee> employees;

    public Directory(List<Employee> employees) {
        this.employees = employees;
    }

    public Directory() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployeesByExperience(int experience) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience() == experience) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<String> getPhoneByFirstName(String firstName) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getFirstName().equalsIgnoreCase(firstName)) {
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers;
    }

    public Employee getEmployeeByPersonnelNumber(int personnelNumber) {
        for (Employee employee : employees) {
            if (employee.getPersonnelNumber() == personnelNumber) {
                return employee;
            }
        }
        return null;
    }

    public void addEmployee(String phoneNumber, String firstName, String secondName, int experience) {
        Employee employee = new Employee(phoneNumber, firstName, secondName, experience);
        employees.add(employee);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
}
