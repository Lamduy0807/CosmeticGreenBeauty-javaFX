/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.DetailExport;

/**
 *
 * @author Duy
 * DAO interface for DetailExport
 */
public interface DetailExportDAO {
    boolean InsertToDatabase(DetailExport detail, int id);
}
