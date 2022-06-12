/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.DetailImport;

/**
 *
 * @author Duy
 * DAO interface for DetailImport
 */
public interface DetailImportDAO {
    boolean InsertToDatabase(DetailImport detail, int id);
}
