/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Employee;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public interface EmployeeDAO {
    ObservableList<Employee> getAllUser();
    Employee getById(int employee_id);
    int addEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int deleteEmployee(int employee_id);  
    
}
