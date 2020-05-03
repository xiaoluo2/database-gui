package com.database.gui;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.database.sql.entity.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DatabaseGuiApplication{

//	public static void main(String[] args) {
//		SpringApplication.run(DatabaseGuiApplication.class, args);
//	}
    
        /**
        * @param args the command line arguments
        */
        public static void main(String args[]) {

//            /* Set the Nimbus look and feel */
//            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//             */
//            try {
//                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                    if ("Nimbus".equals(info.getName())) {
//                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                        break;
//                    }
//                }
//            } catch (ClassNotFoundException ex) {
//                java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            } catch (InstantiationException ex) {
//                java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//                java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            }
//            //</editor-fold>
//            //</editor-fold>
//
//            /* Create and display the form */
//            java.awt.EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    new GuiJFrame().setVisible(true);
//                }
//            });
              List<Entity> list = new ArrayList<Entity>();
              list.add(new Antibody("a","b",1,"c","d"));
              list.add(new Chemical("a","b",1,"c","d"));
              list.add(new Item("a","b",1,"c"));
              list.add(new Lab_Member("a","b","c"));
              list.add(new Location(1,2,"a",3));
              list.add(new Order("a","b","c","d"));
              list.add(new Plasmid("a","b","z","c","d"));
              list.add(new Strain("a","b","b","z"));
              
              list.iterator().forEachRemaining((entity) -> {
                  System.out.println(entity.getTableName() + ": " + String.join(",",entity.getColNames()));
                  System.out.println(String.join(",",entity.getValues()));
              });
        }

}
