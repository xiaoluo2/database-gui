package database.gui.models;

/*

Database Programming with JDBC and Java, Second Edition
By George Reese
ISBN: 1-56592-616-1

Publisher: O'Reilly

*/

/* $Id: RowSetModel.java,v 1.1 1999/03/03 06:00:22 borg Exp $ */
/* Copyright  1999 George Reese, All Rights Reserved */

import database.gui.bean.Bean;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.RowSet;
import javax.swing.table.AbstractTableModel;

public class RowSetModel extends AbstractTableModel{
  private RowSet rowSet = null;
  private Bean bean = null;

  public RowSetModel(RowSet set) {
    super();
    rowSet = set;
  }
  
  public RowSetModel(Bean b){
      this(b.getRowSet());
      this.bean = b;
  }
  
  public Bean getBean(){
      return this.bean;
  }

  @Override
  public Class getColumnClass(int column) {
    String cname;
    int type;

    try {
      ResultSetMetaData meta = rowSet.getMetaData();

      if (meta == null) {
        return null;
      }
      type = meta.getColumnType(column + 1);
    } catch (SQLException e) {
      e.printStackTrace();
      return super.getColumnClass(column);
    }
    switch (type) {
    case Types.BIT: {
      cname = "java.lang.Boolean";
      break;
    }
    case Types.TINYINT: {
      cname = "java.lang.Byte";
      break;
    }
    case Types.SMALLINT: {
      cname = "java.lang.Short";
      break;
    }
    case Types.INTEGER: {
      cname = "java.lang.Integer";
      break;
    }
    case Types.BIGINT: {
      cname = "java.lang.Long";
      break;
    }
    case Types.FLOAT:
    case Types.REAL: {
      cname = "java.lang.Float";
      break;
    }
    case Types.DOUBLE: {
      cname = "java.lang.Double";
      break;
    }
    case Types.NUMERIC: {
      cname = "java.lang.Number";
      break;
    }
    case Types.DECIMAL: {
      cname = "java.math.BigDecimal";
      break;
    }
    case Types.CHAR:
    case Types.VARCHAR:
    case Types.LONGVARCHAR: {
      cname = "java.lang.String";
      break;
    }
    case Types.DATE: {
      cname = "java.sql.Date";
      break;
    }
    case Types.TIME: {
      cname = "java.sql.Time";
      break;
    }
    case Types.TIMESTAMP: {
      cname = "java.sql.Timestamp";
      break;
    }
    case Types.BINARY:
    case Types.VARBINARY:
    case Types.LONGVARBINARY: {
      cname = "byte[]";
      break;
    }
    case Types.OTHER:
    case Types.JAVA_OBJECT: {
      cname = "java.lang.Object";
      break;
    }
    case Types.CLOB: {
      cname = "java.sql.Clob";
      break;
    }
    case Types.BLOB: {
      cname = "java.ssql.Blob";
      break;
    }
    case Types.REF: {
      cname = "java.sql.Ref";
      break;
    }
    case Types.STRUCT: {
      cname = "java.sql.Struct";
      break;
    }
    default: {
      return super.getColumnClass(column);
    }
    }
    try {
      return Class.forName(cname);
    } catch (Exception e) {
      e.printStackTrace();
      return super.getColumnClass(column);
    }
  }

  @Override
  public int getColumnCount() {
    try {
      ResultSetMetaData meta = rowSet.getMetaData();

      if (meta == null) {
        return 0;
      }
      return meta.getColumnCount();
    } catch (SQLException e) {
      return 0;
    }
  }

  @Override
  public String getColumnName(int col) {
    try {
      ResultSetMetaData meta = rowSet.getMetaData();

      if (meta == null) {
        return null;
      }
      return meta.getColumnName(col + 1);
    } catch (SQLException e) {
      return "Error";
    }
  }

  @Override
  public int getRowCount() {
    try {
      if (rowSet.last()) {
        return (rowSet.getRow());
      } else {
        return 0;
      }
    } catch (SQLException e) {
      return 0;
    }
  }

  @Override
  public Object getValueAt(int row, int col) {
    try {
      if (!rowSet.absolute(row + 1)) {
        return null;
      }
      return rowSet.getObject(col + 1);
    } catch (SQLException e) {
      return null;
    }
  }
  
  // non-editable fields
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false;
  }

}