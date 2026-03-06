
package menuutama;

import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class user extends javax.swing.JPanel {
private final DefaultTableModel model;
    public user() {
        initComponents();
        autonumber();
        model = new DefaultTableModel();
        tabledatauser.setModel(model);
        model.addColumn("ID USER ");
        model.addColumn("NAMA");
        model.addColumn("NO TELP");
        model.addColumn("EMAIL");
        model.addColumn("ALAMAT");
        loadtable();   
    }
    
    public void tampil() {
    try {
        Connection co = koneksi.getKoneksi();
        String sql = "SELECT * FROM karyawan WHERE id_akun LIKE '%" + t_idakun.getText() + "%'";
        PreparedStatement ps = co.prepareStatement(sql);
        ResultSet r = ps.executeQuery();
        
        // Declare variables outside the loop
        String username = "";
        String password = "";
        String level = "";
        String kartu = "";
        
        while (r.next()) {
            username = r.getString("username");
            password = r.getString("password");
            level = r.getString("level");
            kartu = r.getString("kartu");
        }
        
        // Set GUI components with retrieved values
        t_username.setText(username);
        t_password.setText(password);
        cblevel.setSelectedItem(level);
        t_idkartu.setText(kartu);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    
    public void cari() {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("ID USER");
        tabel.addColumn("NAMA");
        tabel.addColumn("NO TELP");
        tabel.addColumn("EMAIL");
        tabel.addColumn("ALAMAT");
    
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "Select * from karyawan where id_akun like '%" + txtcari.getText() + "%'" +
                    "or nama_kasir like '%" + txtcari.getText() + "%'";
            
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                });
            }
        tabledatauser.setModel(tabel);
        loadtable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
    } finally {
    }
    }
    
    
    private void autonumber(){
        try {
            java.sql.Connection c = koneksi.getKoneksi();
            java.sql.Statement s = c.createStatement();
            String sql = "SELECT * FROM karyawan ORDER BY id_akun DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String id_akun = r.getString("id_akun").substring(3);
                String AK = ""+(Integer.parseInt(id_akun)+1);
                String Nol = "";

               if (AK.length()==1)
                {Nol="0";}
               else if(AK.length()==2)
               {Nol="";}
                t_idakun.setText("AKN"+Nol+AK);
            } else{
                t_idakun.setText("AKN01");
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
            
            String sql = "SELECT * FROM karyawan";
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                Object[] o = new Object[5];
                o [0] = r.getString("id_akun");
                o [1] = r.getString("nama_kasir");
                o [2] = r.getString("no_telp");
                o [3] = r.getString("email");
                o [4] = r.getString("alamat");
                model.addRow(o);
                
            }
           r.close();
           s.close();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
  
  
         public void kosong(){
        
        t_nama.setText("");
        t_username.setText("");
        t_email.setText(""); 
        t_notelp.setText(""); 
        t_alamat.setText("");
        t_password.setText("");
        t_idkartu.setText("");
        loadtable();
        btnsimpan.setEnabled(true);
   } 
  
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        t_email = new javax.swing.JTextField();
        cblevel = new javax.swing.JComboBox<>();
        btnsimpan = new javax.swing.JButton();
        t_alamat = new javax.swing.JTextField();
        btnedit = new javax.swing.JButton();
        t_username = new javax.swing.JTextField();
        t_nama = new javax.swing.JTextField();
        t_notelp = new javax.swing.JTextField();
        t_idakun = new javax.swing.JTextField();
        txtcari = new javax.swing.JTextField();
        btnhapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabledatauser = new javax.swing.JTable();
        t_idkartu = new javax.swing.JTextField();
        t_password = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_email.setBackground(new java.awt.Color(93, 119, 114));
        t_email.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_email.setBorder(null);
        t_email.setCaretColor(new java.awt.Color(93, 113, 119));
        t_email.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        t_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_emailActionPerformed(evt);
            }
        });
        add(t_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 421, 240, 31));

        cblevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "karyawan" }));
        add(cblevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 585, 340, 36));

        btnsimpan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setContentAreaFilled(false);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 678, 100, 40));

        t_alamat.setBackground(new java.awt.Color(93, 119, 114));
        t_alamat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_alamat.setBorder(null);
        t_alamat.setCaretColor(new java.awt.Color(93, 113, 119));
        t_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_alamatActionPerformed(evt);
            }
        });
        add(t_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 532, 270, 31));

        btnedit.setText("Edit");
        btnedit.setContentAreaFilled(false);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 678, 100, 40));

        t_username.setBackground(new java.awt.Color(93, 119, 114));
        t_username.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_username.setBorder(null);
        t_username.setCaretColor(new java.awt.Color(93, 113, 119));
        t_username.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(t_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 312, 260, 31));

        t_nama.setBackground(new java.awt.Color(93, 119, 114));
        t_nama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_nama.setBorder(null);
        t_nama.setCaretColor(new java.awt.Color(93, 113, 119));
        t_nama.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(t_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 257, 243, 31));

        t_notelp.setBackground(new java.awt.Color(93, 119, 114));
        t_notelp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_notelp.setBorder(null);
        t_notelp.setCaretColor(new java.awt.Color(93, 113, 119));
        add(t_notelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 479, 243, 31));

        t_idakun.setBackground(new java.awt.Color(93, 119, 114));
        t_idakun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_idakun.setBorder(null);
        t_idakun.setCaretColor(new java.awt.Color(93, 113, 119));
        t_idakun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_idakunActionPerformed(evt);
            }
        });
        add(t_idakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 146, 243, 31));

        txtcari.setBackground(new java.awt.Color(93, 113, 119));
        txtcari.setBorder(null);
        txtcari.setCaretColor(new java.awt.Color(93, 113, 119));
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
        add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 182, 280, 35));

        btnhapus.setText("Hapus");
        btnhapus.setContentAreaFilled(false);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 673, 130, 50));

        tabledatauser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Akun", "Nama", "Username", "Password", "No Telp", "Alamat"
            }
        ));
        tabledatauser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabledatauserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabledatauser);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 640, 270));

        t_idkartu.setBackground(new java.awt.Color(93, 119, 114));
        t_idkartu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_idkartu.setText("\n");
        t_idkartu.setBorder(null);
        add(t_idkartu, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 202, 243, 31));

        t_password.setBackground(new java.awt.Color(93, 119, 114));
        t_password.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        t_password.setBorder(null);
        t_password.setCaretColor(new java.awt.Color(93, 113, 119));
        t_password.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        t_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_passwordActionPerformed(evt);
            }
        });
        add(t_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 368, 240, 31));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\OneDrive\\Dokumen\\NetBeansProjects\\numnum\\src\\pct\\karyawan.png")); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1390, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void t_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_emailActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        try {
            // kode untuk menentukan panjang minimal password
            String password = t_password.getText();
            if (password.length() <6) {
                JOptionPane.showMessageDialog(null, "Maaf, Password Minimal 6 Karakter");
                return; // untuk menghentikan run ketika password kurang dari 6 karakter
            }
           
           String notelp = t_notelp.getText();
           if (!notelp.matches("[0-9]+")) {
               JOptionPane.showMessageDialog(null, "Maaf! No Telepon Harus Berisi Angka");
               return;
           }
           
           if (notelp.length() < 11) {
               JOptionPane.showMessageDialog(null, "Maaf! No Telepon harus terdiri dari setidaknya 11 karakter");
               return;
           }
            
            
            String sql = "INSERT INTO karyawan VALUES ('"+t_idakun.getText() +"','"+t_nama.getText() +"','"+t_username.getText()+"','"+t_password.getText()+"','" +t_notelp.getText() +"','" +t_email.getText() +"','"+t_alamat.getText() +"','"+cblevel.getSelectedItem().toString()+"','" +t_idkartu.getText() +"')";
            java.sql.Connection conn = (java.sql.Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DISIMPAN");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        kosong();
        autonumber();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        if (t_nama.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Pilih Dulu Data Yang Akan Diedit!");
        }else{
            {
                try {
                    String sql =( "update karyawan set nama_kasir='"+
                        t_nama.getText() +"' ,username='" +
                        t_username.getText()+ "',password='" +
                        t_password.getText()+ "',no_telp='" +
                        t_notelp.getText()+ "',email='" +
                        t_email.getText() +"',alamat='" +
                        t_alamat.getText() +"',level='" +
                        cblevel.getSelectedItem().toString()+"',kartu='" +
                        t_idkartu.getText() +"' where id_akun='" + t_idakun.getText() + "'");
                    java.sql.Connection conn = (java.sql.Connection) koneksi.getKoneksi();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    loadtable();
                    kosong();
                    autonumber();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diedit ");
                    t_nama.setEnabled(true);
                    t_username.setEnabled(true);
                    t_password.setEnabled(true);
                    t_notelp.setEnabled(true);
                    t_email.setEnabled(true);
                    t_alamat.setEnabled(true);
                    btnsimpan.setEnabled(true);
                    
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        }
    }//GEN-LAST:event_btneditActionPerformed

    private void t_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_alamatActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        cari();
    }//GEN-LAST:event_txtcariKeyTyped

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        if (t_nama.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Data Yang Akan Dihapus!");

        }else{
            int jawab = JOptionPane.showConfirmDialog(null, "Data Ini Akan Dihapus, "
                + "Apakah anda Ingin Melanjutkannya?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab == 0) {
                try {
                    java.sql.Connection c = koneksi.getKoneksi();
                    java.sql.Statement s = c.createStatement();
                    String sql = "DELETE FROM karyawan WHERE id_akun = '" + t_idakun.getText() + "'";
                    s.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    kosong();
                    loadtable();
                    autonumber();
                    btnsimpan.setEnabled(true);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void tabledatauserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabledatauserMouseClicked
        t_idakun.setText(tabledatauser.getValueAt(tabledatauser.getSelectedRow(), 0).toString());
        t_nama.setText(tabledatauser.getValueAt(tabledatauser.getSelectedRow(), 1).toString());
        t_notelp.setText(tabledatauser.getValueAt(tabledatauser.getSelectedRow(), 2).toString());
        t_email.setText(tabledatauser.getValueAt(tabledatauser.getSelectedRow(), 3).toString());
        t_alamat.setText(tabledatauser.getValueAt(tabledatauser.getSelectedRow(), 4).toString());
        tampil();
        btnsimpan.setEnabled(false);
    }//GEN-LAST:event_tabledatauserMouseClicked

    private void t_idakunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_idakunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_idakunActionPerformed

    private void t_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_passwordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cblevel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField t_alamat;
    private javax.swing.JTextField t_email;
    private javax.swing.JTextField t_idakun;
    private javax.swing.JTextField t_idkartu;
    private javax.swing.JTextField t_nama;
    private javax.swing.JTextField t_notelp;
    private javax.swing.JTextField t_password;
    private javax.swing.JTextField t_username;
    private javax.swing.JTable tabledatauser;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
