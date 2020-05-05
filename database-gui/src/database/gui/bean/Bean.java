/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Entity;
import javax.sql.RowSet;
import javax.swing.JPanel;

/**
 *
 * @author Xiao Luo
 */
public abstract class Bean {
    
    public abstract JPanel getForm();
    
    public abstract Entity create(Entity e);
    
    public abstract Entity update(Entity e);
    
    public abstract void delete(Entity e);
    
    public abstract Entity getCurrent();
    
    public abstract RowSet getRowSet();
}
