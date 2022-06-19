/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Payslip;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public interface PayslipDAO {
     ObservableList<Payslip> getAllPayslip();
    int addPayslip(Payslip payslip);
    
}
