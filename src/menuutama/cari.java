package menuutama;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class cari extends javax.swing.JFrame {
         private DefaultTableModel model;
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "SELECT * FROM menu";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[3];
                o [0] = r.getString("id_menu");
                o [1] = r.getString("nama");
                o [2] = r.getString("hargadnt");
                
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cari() {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Donat");
        tabel.addColumn("Harga");
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "Select * from menu where id_menu like '%" + txtcari.getText() + "%'" +
                    "or nama like '%" + txtcari.getText() + "%'";
            
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                });
            }
        tabell.setModel(tabel);
        loadData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
    } finally {
    }
    }
    public cari() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        model = new DefaultTableModel();
        tabell.setModel(model);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Donat");
        model.addColumn("Harga");
        
        
    }

   
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtcari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabell = new javax.swing.JTable();
        btnpilih = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnpilih1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcari.setBackground(new java.awt.Color(93, 113, 119));
        txtcari.setForeground(new java.awt.Color(93, 113, 119));
        txtcari.setBorder(null);
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcariKeyTyped(evt);
            }
        });
        getContentPane().add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 108, 180, 23));

        tabell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabell);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 590, 230));

        btnpilih.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnpilih.setText("Pilih");
        btnpilih.setContentAreaFilled(false);
        btnpilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpilihActionPerformed(evt);
            }
        });
        getContentPane().add(btnpilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 387, 90, 30));

        btnbatal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.setContentAreaFilled(false);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 387, 80, 30));

        btnpilih1.setBackground(new java.awt.Color(153, 153, 255));
        btnpilih1.setContentAreaFilled(false);
        btnpilih1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpilih1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnpilih1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 20, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\OneDrive\\Dokumen\\NetBeansProjects\\numnum\\src\\pct\\pilihan.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        cari();
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        cari();
    }//GEN-LAST:event_txtcariKeyTyped

    private void btnpilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpilihActionPerformed
        // TODO add your handling code here:
        int i = tabell.getSelectedRow();

        String id = tabell.getValueAt(i, 0).toString();
        String nama = tabell.getValueAt(i, 1).toString();
        String harga = tabell.getValueAt(i, 2).toString();

        transaksii.txkodebarang.setText(id);
        transaksii.txnama1.setText(nama);
        transaksii.txharga.setText(harga);

        dispose();
    }//GEN-LAST:event_btnpilihActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnpilih1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpilih1ActionPerformed
        cari();
    }//GEN-LAST:event_btnpilih1ActionPerformed

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
            java.util.logging.Logger.getLogger(cari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cari().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    public static javax.swing.JButton btnpilih;
    private javax.swing.JButton btnpilih1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabell;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
