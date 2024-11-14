package mysqlapplication;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
    

public class InsertTable extends javax.swing.JFrame {
        
    private MyDBConnection mdbc;
    private Statement stmt;
    private EditDialog dlg;

    public InsertTable() {
        
        dlg =  new EditDialog(this, true);
        try{
            mdbc = new MyDBConnection();
            mdbc.init();
            Connection conn = mdbc.getMyConnection();
            stmt = conn.createStatement();
            initComponents();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IDBookLabel = new javax.swing.JLabel();
        AuthorBookLabel = new javax.swing.JLabel();
        NameBookLabel = new javax.swing.JLabel();
        YearBookLabel = new javax.swing.JLabel();
        CostBookLabel = new javax.swing.JLabel();
        IsNewBookLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        SendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        BookTable = new javax.swing.JTable();
        CommentLabel = new javax.swing.JLabel();
        EditButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        IDBookLabel.setText("ID");

        AuthorBookLabel.setText("Author");

        NameBookLabel.setText("Name");

        YearBookLabel.setText("Year");

        CostBookLabel.setText("Cost");

        IsNewBookLabel.setText("IsNew");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jTextField5.setText("jTextField5");

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        SendButton.setText("Send");
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        ResultSet rs = getResultFromBooks();
        BookTable.setModel(new BookTableModel(rs));
        mdbc.close(rs);
        jScrollPane1.setViewportView(BookTable);

        CommentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CommentLabel.setText("Click the button to send data");

        EditButton.setText("Edit");
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(EditButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DeleteButton)
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CommentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SendButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IsNewBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AuthorBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(YearBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CostBookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1))))
                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDBookLabel)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AuthorBookLabel)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameBookLabel)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(YearBookLabel)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CostBookLabel)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IsNewBookLabel)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addComponent(SendButton)
                .addGap(18, 18, 18)
                .addComponent(CommentLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditButton)
                    .addComponent(DeleteButton))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void BookTableMouseClicked(java.awt.event.MouseEvent evt) {                                      
        if (BookTable.getSelectedRowCount() > 0) {
            EditButton.setEnabled(true);
            DeleteButton.setEnabled(true);
        } else {
            EditButton.setEnabled(false);
            DeleteButton.setEnabled(false);
        }
    }
    
    public String quotate(String content) {
        return "'" + content + "'";
    }
    
    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed
        boolean isValid = false;
        
        while (!isValid) {
            try {
                String BookID = jTextField1.getText();
                String Author = jTextField2.getText();
                String BookName = jTextField3.getText();
                String Year = jTextField4.getText();
                String Cost = jTextField5.getText();
                boolean isNew = jCheckBox1.isSelected();
                String insertStr = "";

                int costValue;
                try {
                    costValue = Integer.parseInt(Cost);
                    if (costValue < 1) {
                        CommentLabel.setText("Cost has to be positiv");
                        return;
                    }
                } catch (NumberFormatException e) {
                    CommentLabel.setText("Cost has to be integer value");
                    return;
                }

                int yearValue = 0;
                try {
                    yearValue = Integer.parseInt(Year);
                    if (yearValue < 1 || yearValue > 2025) {
                        CommentLabel.setText("Year has to be lower than 2026");
                        return;
                    }
                } catch (NumberFormatException e) {
                    CommentLabel.setText("Year has to be integer value");
                    return;
                }

                ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE ID = " + quotate(BookID));
                if (rs.next()) {
                    CommentLabel.setText("Object with this ID already exists");
                    return;
                }

                try {
                    insertStr = "INSERT INTO book VALUES ("
                            + quotate(BookID) + ","
                            + quotate(Author) + ","
                            + quotate(BookName) + ","
                            + quotate(Year) + ","
                            + quotate(Cost) + ","
                            + (isNew ? "1" : "0") + ")";

                    int done = stmt.executeUpdate(insertStr);
                    CommentLabel.setText("1 row inserted");
                    getContentPane().removeAll();
                    initComponents();
                    
                    isValid = true;
                }
                catch(SQLException e) {
                    e.printStackTrace();
                    CommentLabel.setText("Error occurred in inserting data");
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SendButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (stmt != null && !stmt.isClosed()) {
                ResultSet rs = stmt.getResultSet();
                if (rs != null && !rs.isClosed()) {
                    rs.close(); 
                }
                stmt.close(); 
            }

            mdbc.destroy();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        dlg.setBookID((String) BookTable.getValueAt(BookTable.getSelectedRow(), 0));               
        dlg.setAuthor((String) BookTable.getValueAt(BookTable.getSelectedRow(), 1));            
        dlg.setBookName((String) BookTable.getValueAt(BookTable.getSelectedRow(), 2));
        dlg.setYear((String) BookTable.getValueAt(BookTable.getSelectedRow(), 3));
        dlg.setCost((String) BookTable.getValueAt(BookTable.getSelectedRow(), 4));
        dlg.setIsNew("1".equals(BookTable.getValueAt(BookTable.getSelectedRow(), 5).toString()));        
        dlg.setVisible(true);

        try {
            String originalBookID = (String) BookTable.getValueAt(BookTable.getSelectedRow(), 0);

            String updateStr = "UPDATE book SET "
                    + "ID=" + quotate(dlg.getBookID()) + ", "
                    + "Author=" + quotate(dlg.getAuthor()) + ", "
                    + "Name=" + quotate(dlg.getBookName()) + ", "
                    + "Year=" + quotate(dlg.getYear()) + ", "
                    + "Cost=" + quotate(dlg.getCost()) + ", "
                    + "IsNew=" + (dlg.getIsNew() ? "1" : "0") 
                    + " WHERE ID=" + quotate(originalBookID);

            int done = stmt.executeUpdate(updateStr);

            if (done > 0) {
                CommentLabel.setText("1 row updated");
                getContentPane().removeAll();
                initComponents();
            }
        } 
        catch (Exception e) {
            System.out.println("Error occurred in editing data");
        }	
    }//GEN-LAST:event_EditButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        String BookID = (String) BookTable.getValueAt(BookTable.getSelectedRow(), 0);
        String deleteStr = "";

        try {
            deleteStr = "DELETE FROM book WHERE ID=" + quotate(BookID);
            int done = stmt.executeUpdate(deleteStr);
            CommentLabel.setText("1 row deleted");
            getContentPane().removeAll();
            initComponents();
        } catch (SQLException e) {
            e.printStackTrace();
            CommentLabel.setText("Error occurred in deleting data");
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InsertTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertTable().setVisible(true);
            }
        });
    }
    
    public ResultSet getResultFromBooks(){
        ResultSet rs = null;
        try{
            rs = stmt.executeQuery("SELECT * FROM book");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AuthorBookLabel;
    private javax.swing.JTable BookTable;
    private javax.swing.JLabel CommentLabel;
    private javax.swing.JLabel CostBookLabel;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JLabel IDBookLabel;
    private javax.swing.JLabel IsNewBookLabel;
    private javax.swing.JLabel NameBookLabel;
    private javax.swing.JButton SendButton;
    private javax.swing.JLabel YearBookLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
