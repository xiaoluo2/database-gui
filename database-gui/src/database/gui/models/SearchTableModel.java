/*
Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
*/
package database.gui.models;

import database.gui.entity.Entity;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Xiao Luo
 */
public class SearchTableModel extends AbstractTableModel {
    
    private String[] columnNames;
    private String[][] rowData;

    /** Method from interface TableModel; returns the number of columns
       * @return  */

    @Override
    public int getColumnCount() {
      return columnNames.length;
    }

      /** Method from interface TableModel; returns the number of rows
       * @return  */

    @Override
    public int getRowCount() {
      return rowData.length;
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
        return rowData[rowIndex][columnIndex];
    }

    /** *  Method from interface TableModel; returns true if the specified cell
    * @return 
    */

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return true;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
          rowData[row][column] = (String) value;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        
    }

    public void populateTable(Entity[] items){
        for(int i=0; i< items.length; i++){
            rowData[i] = items[i].getValues();
        }
    }
    
    public void setFieldNames(Entity e){
        this.columnNames = e.getColNames();
    }
}
