package org.nif.neurolex;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class MainFrame extends javax.swing.JFrame {

    public Thread tr;

    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtURL = new javax.swing.JTextField();
        TxtWikiUserName = new javax.swing.JTextField();
        TxtServerURL = new javax.swing.JTextField();
        TXTDBUserName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtAttribute = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtElementsReplaced = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TxtElementsReplacedWith = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        previewButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        Statuts = new javax.swing.JLabel();
        convertButton = new javax.swing.JButton();
        TxtWikiPassword = new javax.swing.JPasswordField();
        TXTDBUserPassword = new javax.swing.JPasswordField();
        stopButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        CheckSensative = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bulk Change");
        setResizable(false);

        jLabel1.setText("API page URL:");

        jLabel2.setText("Database Server URL:");

        jLabel3.setText("Database Username:");

        jLabel4.setText("Database Password:");

        jLabel5.setText("MediaWiki Username:");

        jLabel6.setText("MediaWiki Password:");

        TxtURL.setName(""); // NOI18N

        jLabel7.setText("Attribute:");

        jLabel8.setText("Ex: Has role");

        jLabel9.setText("Elements to be replaced:");

        jLabel10.setText("Ex:Downloadable Data,Web Database");

        jLabel11.setText("To be replaced with:");

        jLabel12.setText("Ex: Database");

        previewButton.setText("Preview");
        previewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Click_Preview(evt);
            }
        });

        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        jScrollPane1.setViewportView(txtOutput);

        jLabel13.setText("Output:");

        convertButton.setText("Replace");
        convertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Click_Convert(evt);
            }
        });

        stopButton.setText("Stop!");
        stopButton.setEnabled(false);
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Click_Stop(evt);
            }
        });

        CheckSensative.setText("Case Sensative");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtWikiUserName)
                                    .addComponent(TxtURL)
                                    .addComponent(TxtWikiPassword)
                                    .addComponent(TxtServerURL, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel11))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTDBUserName)
                                    .addComponent(TXTDBUserPassword)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TxtElementsReplacedWith, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(TxtElementsReplaced, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                            .addComponent(TxtAttribute, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel12)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(CheckSensative)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(83, 83, 83))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(previewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(convertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Statuts, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel13)))
                        .addContainerGap(666, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtWikiUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtWikiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtServerURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTDBUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TXTDBUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(TxtAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(CheckSensative)
                            .addComponent(TxtElementsReplaced))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(TxtElementsReplacedWith, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(previewButton)
                        .addComponent(convertButton)
                        .addComponent(stopButton))
                    .addComponent(Statuts, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );

        TxtURL.getAccessibleContext().setAccessibleName("");
        TxtURL.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Click_Preview(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Click_Preview
        try {

            String output = Input_Check();
            if (!(output.equals(""))) {
                JOptionPane.showMessageDialog(rootPane, output);
            } else {
                stopButton.setEnabled(true);
                Runnable rn = new ResourceFix(this, "preview", TxtAttribute.getText().trim(), TxtElementsReplaced.getText().trim(), TxtElementsReplacedWith.getText().trim());
                Thread tr = new Thread(rn);
                tr.start();
                previewButton.setEnabled(false);
                Statuts.setText("Loading Data... Please wait!");
            }
        } catch (Exception e) {
            stopButton.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, e.getMessage());

        }

    }//GEN-LAST:event_Click_Preview

    private void Click_Convert(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Click_Convert
        try {

            String output = Input_Check();
            if (!(output.equals(""))) {
                JOptionPane.showMessageDialog(rootPane, output);
            } else {
                stopButton.setEnabled(true);
                Runnable rn = new ResourceFix(this, "update", TxtAttribute.getText().trim(), TxtElementsReplaced.getText().trim(), TxtElementsReplacedWith.getText().trim());
                tr = new Thread(rn);
                tr.start();
                previewButton.setEnabled(false);
                Statuts.setText("Loading Data... Please wait!");
            }
        } catch (Exception e) {
            stopButton.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_Click_Convert

    private void Click_Stop(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Click_Stop
        try {
            tr.stop();
        } catch (Exception ex) {
        }
        stopButton.setEnabled(false);
        previewButton.setEnabled(true);
        convertButton.setEnabled(true);
        txtOutput.append("The thread was terminated!\n");
        Statuts.setText("");
        // TODO add your handling code here:

    }//GEN-LAST:event_Click_Stop

    public String Input_Check() {
        String output = "";
        if (TxtURL.getText().trim().equals("")) {
            output += "Please enter API page URL!\n";
        }
        if (TxtWikiUserName.getText().trim().equals("")) {
            output += "Please enter MediaWiki user name!\n";
        }
        if (TxtWikiPassword.getText().trim().equals("")) {
            output += "Please enter MediaWiki password!\n";
        }
        if (TxtServerURL.getText().trim().equals("")) {
            output += "Please enter database server URL!\n";
        }
        if (TXTDBUserName.getText().trim().equals("")) {
            output += "Please enter database user name!\n";
        }
        if (TXTDBUserName.getText().trim().equals("")) {
            output += "Please enter database user name!\n";
        }
        if (TXTDBUserPassword.getText().trim().equals("")) {
            output += "Please enter database password!\n";
        }
        if (TxtAttribute.getText().trim().equals("")) {
            output += "Please enter attribute!\n";
        }
        if (TxtElementsReplaced.getText().trim().equals("")) {
            output += "Please enter the elements to be replaced!\n";
        }
        if (TxtElementsReplaced.getText().trim().equals("")) {
            output += "Please enter the elements to be replaced with!\n";
        }
        return output;
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(rootPane, errorMessage);
    }

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox CheckSensative;
    public javax.swing.JLabel Statuts;
    public javax.swing.JTextField TXTDBUserName;
    public javax.swing.JPasswordField TXTDBUserPassword;
    public javax.swing.JTextField TxtAttribute;
    public javax.swing.JTextField TxtElementsReplaced;
    public javax.swing.JTextField TxtElementsReplacedWith;
    public javax.swing.JTextField TxtServerURL;
    public javax.swing.JTextField TxtURL;
    public javax.swing.JPasswordField TxtWikiPassword;
    public javax.swing.JTextField TxtWikiUserName;
    public javax.swing.JButton convertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JButton previewButton;
    public javax.swing.JButton stopButton;
    public javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
