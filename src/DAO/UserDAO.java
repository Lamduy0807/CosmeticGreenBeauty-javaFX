/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;


import Model.User;


/**
 *
 * @author Duy
 * DAO interface for User
 */
public interface UserDAO {
    User getUserInformationByUsername(String un);
}
