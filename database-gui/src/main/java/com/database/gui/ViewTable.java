/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.gui;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Xiao Luo
 */
public class ViewTable extends AbstractTableModel {

    private String[] columnNames;
    private Object[][] rowData;
    private String itemType;
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public int getRowCount() { return rowData.length; }
    @Override
    public int getColumnCount() { return columnNames.length; }
    @Override
    public Object getValueAt(int row, int col) {
        return rowData[row][col];
    }
    @Override
    public boolean isCellEditable(int row, int col)
        { return false; }
    
    @Override
    public void setValueAt(Object entry, int row, int col) {
        rowData[row][col] = entry;
    }
    
}
