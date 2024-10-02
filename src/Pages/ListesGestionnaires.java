/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static javax.swing.text.html.HTML.Attribute.ID;
import raven.toast.Notifications;

/**
 *
 * @author safia seid
 */
public class ListesGestionnaires extends javax.swing.JFrame {

    /**
     * Creates new form ListesGestionnaires
     */
    public ListesGestionnaires() {
        initComponents();
        affichageTable();
        Connection conn = null;

    }

  public void affichageTable() {
    Connection conn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devjava", "root", "");
        java.sql.Statement stm = conn.createStatement();
        String sql = "select * from utilisateurs";
        java.sql.ResultSet rest = stm.executeQuery(sql);

        java.util.Date datecreation = new java.util.Date();

        // Vide le tableau avant d'ajouter de nouvelles lignes
        DefaultTableModel tabmodel = (DefaultTableModel) TableAdmin.getModel();
        tabmodel.setRowCount(0);

        while (rest.next()) {
            String id = String.valueOf(rest.getInt("utilisateur_id"));
            String Prenom = rest.getString("prenom");
            String nom = rest.getNString("nom");
            String username = rest.getString("username");

            String role = rest.getString("role");
            String Statut = rest.getString("statut");
            datecreation = rest.getDate("DateInscription");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String dateString = sdf.format(datecreation);

            String tbData[] = {id, Prenom, nom, username, role, Statut, dateString};

            // Ajoute les données à la table
            tabmodel.addRow(tbData);
        }
        conn.close();
        //System.out.println("succeful");
    } catch (Exception e) {
        JOptionPane.showConfirmDialog(null, e);
    }
}

    public void activerCompte() {
        // Vérifie si une ligne est sélectionnée dans le tableau
        int selectedRow = TableAdmin.getSelectedRow();
        if (selectedRow != -1) {
            // Récupère l'ID de la ligne sélectionnée
            String id = (String) TableAdmin.getValueAt(selectedRow, 0);
        
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devjava", "root", "");

                // Prépare la requête SQL pour activer le compte du client
                PreparedStatement ps = conn.prepareStatement("UPDATE utilisateurs SET statut = ? WHERE utilisateur_id = ?");
                ps.setBoolean(1, true);  // Compte activé
                ps.setString(2, id);  // ID du client à mettre à jour
                ps.executeUpdate();  // Exécute la requête

                //JOptionPane.showMessageDialog(null, "Compte activé avec succès pour le client ID : " + id);
               Notifications.getInstance().show(Notifications.Type.SUCCESS,Notifications.Location.TOP_CENTER,"Compte activé avec succès pour le client ");

                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListesClients.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un gestionnaire.");
        }
    }
    public void desactiverCompte() {
        
        int selectedRow = TableAdmin.getSelectedRow();
        if (selectedRow != -1) {
            
            String id = (String) TableAdmin.getValueAt(selectedRow, 0);
        
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devjava", "root", "");

                // Prépare la requête SQL pour activer le compte du client
                PreparedStatement ps = conn.prepareStatement("UPDATE utilisateurs SET statut = ? WHERE utilisateur_id = ?");
                ps.setBoolean(1, false);  // Compte activé
                ps.setString(2, id);  
                ps.executeUpdate();  

                //JOptionPane.showMessageDialog(null, "Compte desactivé avec succès pour le client ID : " + id);
                         Notifications.getInstance().show(Notifications.Type.SUCCESS,Notifications.Location.TOP_CENTER,"Compte desactivé avec succès pour le client ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListesClients.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
           // JOptionPane.showMessageDialog(null, "Veuillez sélectionner un gestionnaire.");
                    Notifications.getInstance().show(Notifications.Type.SUCCESS,Notifications.Location.TOP_CENTER,"veuillez sélectionner un gestionnaire.");

        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableAdmin = new javax.swing.JTable();
        recher = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtsearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Activerbtn = new javax.swing.JButton();
        Desactiverbtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        TableAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Prenom", "Nom", "Username", "Role", "Statut", "DateInscrit"
            }
        ));
        jScrollPane1.setViewportView(TableAdmin);

        recher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherActionPerformed(evt);
            }
        });
        recher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recherKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Listes des Gestionnaires");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel1)
                .addContainerGap(354, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(0, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Ajouter un Gestionnaire");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtsearch.setText("Recherche");
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });

        Activerbtn.setBackground(new java.awt.Color(0, 153, 255));
        Activerbtn.setForeground(new java.awt.Color(255, 255, 255));
        Activerbtn.setText("Activer");
        Activerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActiverbtnActionPerformed(evt);
            }
        });

        Desactiverbtn.setBackground(new java.awt.Color(255, 0, 51));
        Desactiverbtn.setText("Desactiver");
        Desactiverbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesactiverbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Activerbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Desactiverbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Activerbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(Desactiverbtn)
                .addGap(61, 61, 61))
        );

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setText("Sortie");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtsearch)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(recher, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsearch))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new AjoutGestionnaire().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void recherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_recherActionPerformed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) TableAdmin.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        TableAdmin.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(recher.getText()));

    }//GEN-LAST:event_txtsearchActionPerformed

    private void recherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherKeyPressed
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) TableAdmin.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        TableAdmin.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(recher.getText()));
    }//GEN-LAST:event_recherKeyPressed

    private void ActiverbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActiverbtnActionPerformed
           
            activerCompte();
             Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Compte Activer");

             affichageTable();
    }//GEN-LAST:event_ActiverbtnActionPerformed

    private void DesactiverbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactiverbtnActionPerformed
                desactiverCompte();
                 Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Compte desactiver");

                 affichageTable();
    }//GEN-LAST:event_DesactiverbtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: dispose();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sortie");

        new DashboardAdmin().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ListesGestionnaires.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListesGestionnaires.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListesGestionnaires.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListesGestionnaires.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListesGestionnaires().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Activerbtn;
    private javax.swing.JButton Desactiverbtn;
    private javax.swing.JTable TableAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField recher;
    private javax.swing.JButton txtsearch;
    // End of variables declaration//GEN-END:variables
}
