/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuutama;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author ASUS
 */
public class rasa extends javax.swing.JPanel {
    private final DefaultTableModel model;
    
    public rasa() {
        initComponents();
        autonumber();
        model = new DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("ID Rasa");
        model.addColumn("Nama Rasa");
        model.addColumn("Harga");
        loadtable(); 
    }
    
    public void cari() {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("ID Rasa");
        tabel.addColumn("Nama Rasa");
        tabel.addColumn("Harga");
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "Select * from rasa where id_rasa like '%" + txtcari.getText() + "%'" +
                    "or nama_rasa like '%" + txtcari.getText() + "%'";
            
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                });
            }
        jTable1.setModel(tabel);
        loadtable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
    } finally {
    }}
    
    private void autonumber(){
        try {
            java.sql.Connection c = koneksi.getKoneksi();
            java.sql.Statement s = c.createStatement();
            String sql = "SELECT * FROM rasa ORDER BY id_rasa DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String id_menu = r.getString("id_rasa").substring(4);
                String RS = ""+(Integer.parseInt(id_menu)+1);
                String Nol = "";

               if (RS.length()==1)
                {Nol="0";}
               else if(RS.length()==2)
               {Nol="";}
                t_idrasa.setText("RASA"+Nol+RS);
            } else{
                t_idrasa.setText("RASA01");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     private void loadtable(){
       model.getDataVector().removeAllElements();
       model.fireTableDataChanged();
       try {
         Connection c = (Connection) koneksi.getKoneksi();
            Statement s = (Statement) c.createStatement();
            
            String sql = "SELECT * FROM rasa";
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                Object[] o = new Object[3];
                o [0] = r.getString("id_rasa");
                o [1] = r.getString("nama_rasa");
                o [2] = r.getString("harga");
                
                model.addRow(o);
                
              
            }
           r.close();
           s.close();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
     
     private void Bersih(){
        autonumber();
        t_rasa.setText("");
        t_harga.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        t_harga = new javax.swing.JTextField();
        t_idrasa = new javax.swing.JTextField();
        t_rasa = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_harga.setBackground(new java.awt.Color(93, 119, 114));
        t_harga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        t_harga.setBorder(null);
        add(t_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 268, 202, 31));

        t_idrasa.setBackground(new java.awt.Color(93, 119, 114));
        t_idrasa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        t_idrasa.setBorder(null);
        t_idrasa.setCaretColor(new java.awt.Color(93, 119, 114));
        t_idrasa.setDisabledTextColor(new java.awt.Color(93, 119, 114));
        add(t_idrasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 158, 202, 31));

        t_rasa.setBackground(new java.awt.Color(93, 119, 114));
        t_rasa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        t_rasa.setBorder(null);
        add(t_rasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 213, 202, 31));

        btnsimpan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsimpan.setText("SIMPAN");
        btnsimpan.setContentAreaFilled(false);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 318, 150, 40));

        btnedit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnedit.setText("EDIT");
        btnedit.setContentAreaFilled(false);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 387, 90, -1));

        btnrefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnrefresh.setText("REFRESH");
        btnrefresh.setContentAreaFilled(false);
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });
        add(btnrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 387, -1, -1));

        btnhapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhapus.setText("HAPUS");
        btnhapus.setContentAreaFilled(false);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 387, -1, -1));

        txtcari.setBackground(new java.awt.Color(93, 119, 114));
        txtcari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcari.setBorder(null);
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });
        add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 427, 220, 32));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 730, 200));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\OneDrive\\Dokumen\\NetBeansProjects\\numnum\\src\\pct\\rasa.png")); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        if (t_rasa.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong!");

        }else{
            {
                try {
                    java.sql.Connection c = koneksi.getKoneksi();
                    java.sql.Statement s = c.createStatement();
                    String sql = "INSERT INTO rasa VALUES ('"+t_idrasa.getText() +"','"+t_rasa.getText() +"','"+t_harga.getText()+"')";
                    s.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Rasa Berhasil Disimpan");
                    loadtable();

                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        Bersih();
        loadtable();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
         btnsimpan.setEnabled(true);

        if (t_rasa.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Pilih Dulu Data Yang Akan Diedit!");
        }else{

            {
                try {
                    String sql =( "update rasa set nama_rasa='"+ t_rasa.getText() +"' ,harga='" +
                        t_harga.getText()+ "' where id_rasa='" + t_idrasa.getText() + "'");
                    java.sql.Connection conn = (java.sql.Connection)koneksi.getKoneksi();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    loadtable();
                    Bersih();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diedit ");
                    t_rasa.setEnabled(true);
                    t_harga.setEnabled(true);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        }
        Bersih();
        loadtable();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        Bersih();
        btnsimpan.setEnabled(true);
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        if (t_rasa.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Data Yang Akan Dihapus!");

        }else{
            int jawab = JOptionPane.showConfirmDialog(null, "Data Ini Akan Dihapus, "
                + "Apakah anda Ingin Melanjutkannya?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab == 0) {
                try {
                    java.sql.Connection c = koneksi.getKoneksi();
                    java.sql.Statement s = c.createStatement();
                    String sql = "DELETE FROM rasa WHERE id_rasa = '" + t_idrasa.getText() + "'";
                    s.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    Bersih();
                    loadtable();
                    autonumber();
                    btnsimpan.setEnabled(true);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        t_idrasa.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        t_rasa.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        t_harga.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        btnsimpan.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txtcariKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField t_harga;
    private javax.swing.JTextField t_idrasa;
    private javax.swing.JTextField t_rasa;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
