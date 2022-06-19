/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.EmployeeDAO;
import Db.DbConnection;
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class EmployeeDAOImplement implements EmployeeDAO {

    private static EmployeeDAO instance;
    private Connection connection;
    private static final Logger LOG = Logger.getLogger(EmployeeDAO.class.getName());

    private EmployeeDAOImplement() throws ClassNotFoundException {
        connection = DbConnection.getConnect();
    }

    public static EmployeeDAO getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new EmployeeDAOImplement();
        }
        return instance;
    }

    private Employee setDataIntoResultSet(ResultSet r) throws SQLException {
        String id = r.getString(1);
        int iId = Integer.parseInt(id);
        String sName = r.getString(2);
        String sCitizenID = r.getString(3);
        String sAddress = r.getString(4);
        String sPhone = r.getString(5);
        String sEmail = r.getString(6);
        String sPosition = r.getString(7);
        String sUsername = r.getString(8);
        String sPassword = r.getString(9);
        return new Employee(iId, sName, sCitizenID, sAddress, sPhone, sEmail, sPosition, sUsername, sPassword);
    }

    @Override
    public ObservableList<Employee> getAllUser() {
        String query = "SELECT Employee_id, EmployName, Citizen_id, Address, PhoneNumber, Email, Position, Username, Password FROM Employee";
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = setDataIntoResultSet(resultSet);
                employees.add(employee);
            }

            preparedStatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    @Override
    public Employee getById(int employee_id) {
        String query = "SELECT Employee_id, EmployName, Citizen_id, Address, PhoneNumber, Email, Position, Username, Password FROM Employee WHERE Employee_id ='" + employee_id + "'";
        Employee e = new Employee();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                e = setDataIntoResultSet(resultSet);
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    @Override
    public int addEmployee(Employee e) {
        String query = "INSERT INTO Employee (EmployName, Citizen_id, Address, PhoneNumber, Email, Position, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, e.getEmployName());
            preparedStatement.setString(2, e.getCitizen_id());
            preparedStatement.setString(3, e.getAddress());
            preparedStatement.setString(4, e.getPhoneNumber());
            preparedStatement.setString(5, e.getEmail());
            preparedStatement.setString(6, e.getPosition());
            preparedStatement.setString(7, e.getUsername());
            preparedStatement.setString(8, e.getPassword());

            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.print("Error when inserting!!");
        }
        return result;
    }

    @Override
    public int updateEmployee(Employee e) {
        String query = "UPDATE Employee SET EmployName = ?, Citizen_id = ?, Address= ?, PhoneNumber= ?, Email = ?, Position=?, Username=?, Password=? WHERE Employee_id = ?";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, e.getEmployName());
            preparedStatement.setString(2, e.getCitizen_id());
            preparedStatement.setString(3, e.getAddress());
            preparedStatement.setString(4, e.getPhoneNumber());
            preparedStatement.setString(5, e.getEmail());
            preparedStatement.setString(6, e.getPosition());
            preparedStatement.setString(7, e.getUsername());
            preparedStatement.setString(8, e.getPassword());
            preparedStatement.setInt(9, e.getEmployee_id());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.print("Error when inserting!!");
        }
        return result;
    }

    @Override
    public int deleteEmployee(int employee_id) {
        String query = "DELETE Employee WHERE Employee_id = '" + employee_id + "'";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(query);

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error when inserting!!");
        }
        return result;
    }

    @Override
    public ObservableList<Employee> searchEmployee(String search) {
       String query = "SELECT Employee_id, EmployName, Citizen_id, Address, PhoneNumber, Email, Position, Username, Password FROM Employee WHERE (Employee_id like '" + search + "%' or EmployName like N'" + search + "%')";
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = setDataIntoResultSet(resultSet);
                employees.add(employee);
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

}
