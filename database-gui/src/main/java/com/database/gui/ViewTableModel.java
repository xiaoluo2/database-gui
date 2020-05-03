/*
Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
*/
package com.database.gui;

import javax.swing.table.AbstractTableModel;

import com.database.sql.entity.Entity;
/**
 *
 * @author Xiao Luo
 */
public class ViewTableModel extends AbstractTableModel {
    
    private int numrows;
    private int numcols;
    private String[] columnNames;
    private String[][] rowData;

    /** Method from interface TableModel; returns the number of columns
       * @return  */

    @Override
    public int getColumnCount() {
      return numcols;
    }

      /** Method from interface TableModel; returns the number of rows
       * @return  */

    @Override
    public int getRowCount() {
      return numrows;
    }

    /** Method from interface TableModel; returns the column name at columnIndex
     *  based on information from ResultSetMetaData
       * @return 
     */

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /** *  Method from interface TableModel; returns the most specific superclass for
     *  all cell values in the specified column.To keep things simple, all data
    in the table are converted to String objects; hence, this method returns
    the String class.
       * @param column
       * @return 
     */
    @Override
    public Class getColumnClass(int column) {
      return String.class;
    }

    /** *  Method from interface TableModel; returns the value for the cell specified
     *  by columnIndex and rowIndex.TableModel uses this method to populate
    itself with data from the row set.SQL starts numbering its rows and
    columns at 1, but TableModel starts at 0.
       * @param rowIndex
       * @param columnIndex
       * @return 
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return new Object(); //TODO
    }

    /** *  Method from interface TableModel; returns true if the specified cell
    * @return 
    */

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false;
    }

//    @Override
//    public void setValueAt(Object value, int row, int column) {
//
//    }
//
//    @Override
//    public void addTableModelListener(TableModelListener l) {
//    }
//
//    @Override
//    public void removeTableModelListener(TableModelListener l) {
//    }

    public void populateTable(Entity[] resultSet, int length){
        for (int i=0; i<length - 1; i++) {
            this.rowData[i] = resultSet[i].getValues();
        }
    }
    
    public void setFieldNames(Entity e){
        this.columnNames = e.getColNames();
    }
}
