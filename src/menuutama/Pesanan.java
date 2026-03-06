
package menuutama;

import java.awt.HeadlessException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import koneksi.koneksi;
import static menuutama.transaksii.rsharga;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Pesanan extends javax.swing.JPanel {

     String rasa = "";
    int hargatp;
    int hargars;
    int totalHarga;
    String Tanggal;
    String tgl;
    private DefaultTableModel model;
    public void clear2() {
        txkodebarang.setText("");
        txnama1.setText("");
        txharga.setText("");
        txjumlah.setText("");
        totaldnt.setText("");
        rsharga.setText(null);
        rsjumlah.setText("");
        totalrs.setText("");
        cbrasa.setSelectedItem(null);
    }
    
    public void totalBiaya() {
        int jumlahbaris = tbl_list.getRowCount();
        
        totalHarga = 0;
        for (int i = 0; i < jumlahbaris; i++) {
//          Total Harga Ngambil Dari Sini Menggunakan Variable (totalHarga)
            totalHarga += Integer.parseInt(tbl_list.getValueAt(i, 7).toString());
            
        DecimalFormat formatRibuan = new DecimalFormat("Rp #,###");
        String formattedTotalHarga = formatRibuan.format(totalHarga);

        txtotalbayar.setText(String.valueOf(totalHarga));
        txtampil.setText(formattedTotalHarga);
        }

    }
   
    private void autonumber() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String sql = "SELECT * FROM pesanan WHERE id_pesanan LIKE 'PS" + currentDate + "%' ORDER BY id_pesanan DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String lastId = r.getString("id_pesanan").substring(10); 
                int nextNumber = Integer.parseInt(lastId) + 1;
                String formattedNumber = String.format("%04d", nextNumber); 
                txpesanan.setText("PS" + currentDate + formattedNumber);
            } else {
                txpesanan.setText("PS" + currentDate + "0001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
     public void loadData() {
        DefaultTableModel model = (DefaultTableModel) tbl_list.getModel();
        model.addRow(new Object[]{
            txpesanan.getText(),
            txkodebarang.getText(),
            txjumlah.getText(),
            totaldnt.getText(),
            getKeyFromValue(idRasaToNamaRasa, cbrasa.getSelectedItem().toString()), 
            rsjumlah.getText(),
            totalrs.getText(),
            txtotalbayar.getText()
        });

    }

     public void utama() {
        DefaultTableModel model = (DefaultTableModel) tbl_list.getModel();
        model.setRowCount(0);
        txpesanan.setText("");
        txNamaCustomer.setText("");
        jDateChooser1.setDate(null);
        txkodebarang.setText("");
        txnama1.setText("");
        txjumlah.setText("");
        txharga.setText("");
        totaldnt.setText("");
        rsharga.setText(null);
        rsjumlah.setText("");
        totalrs.setText("");
        cbrasa.setSelectedItem(null);
        autonumber();
    }
     
     public void clear() {
        txtotalbayar.setText("");
        txbayar.setText("");
        txkembalian.setText("");
        txtampil.setText("");
    }
     
       
       public void tambahtransaksi() {
        int jumlah, harga, total, rs1, rs2;
        int totalrasa, totalfinal;
        
//        total transaksi all
        jumlah = Integer.valueOf(txjumlah.getText());
        harga = Integer.valueOf(txharga.getText());
        rs1 = Integer.valueOf(rsharga.getText());
        rs2 = Integer.valueOf(rsjumlah.getText());
        totalrasa = rs1 * rs2;
        total = jumlah * harga;
        totalfinal = total + totalrasa;

       
        txtotalbayar.setText(String.valueOf(totalfinal));

        loadData();
        totalBiaya();
        clear2();
        txkodebarang.requestFocus();
    }
    private Map<String, String> idRasaToNamaRasa = new HashMap<>();
    private Map<String, String> idRasaToHarga = new HashMap<>();
    
    public Pesanan() {
        initComponents();
        String idAkun = SessionData.getIdAkun();
        txtkasir.setText(idAkun);
        model = new DefaultTableModel();
        tbl_list.setModel(model);
        model.addColumn("Kode Pesanan");
        model.addColumn("ID Menu");
        model.addColumn("Jumlah");
        model.addColumn("Total");
        model.addColumn("Rasa");
        model.addColumn("Jumlah");
        model.addColumn("Total");
        model.addColumn("Subtotal");
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        
        txtanggal.setText(s.format(date));
        txtotalbayar.setText("");
        txbayar.setText("");
        txkembalian.setText("");
        txpesanan.requestFocus();
    }
    
    private String getKeyFromValue(Map<String, String> map, String value) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
        if (Objects.equals(value, entry.getValue())) {
            return entry.getKey();
        }
    }
    return null;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txpesanan = new javax.swing.JTextField();
        txNamaCustomer = new javax.swing.JTextField();
        txtkasir = new javax.swing.JTextField();
        txtanggal = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txkodebarang = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        txnama1 = new javax.swing.JTextField();
        txharga = new javax.swing.JTextField();
        txjumlah = new javax.swing.JTextField();
        totaldnt = new javax.swing.JTextField();
        cbrasa = new javax.swing.JComboBox<>();
        rsharga = new javax.swing.JTextField();
        rsjumlah = new javax.swing.JTextField();
        totalrs = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_list = new javax.swing.JTable();
        btnsimpan = new javax.swing.JButton();
        txtampil = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        cmbstatuspembayaran = new javax.swing.JComboBox<>();
        txtotalbayar = new javax.swing.JTextField();
        txbayar = new javax.swing.JTextField();
        txkembalian = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txpesanan.setBackground(new java.awt.Color(93, 113, 119));
        txpesanan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txpesanan.setBorder(null);
        txpesanan.setCaretColor(new java.awt.Color(93, 113, 119));
        txpesanan.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(txpesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 118, 150, 22));

        txNamaCustomer.setBackground(new java.awt.Color(93, 113, 119));
        txNamaCustomer.setBorder(null);
        txNamaCustomer.setCaretColor(new java.awt.Color(93, 113, 119));
        txNamaCustomer.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txNamaCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNamaCustomerActionPerformed(evt);
            }
        });
        add(txNamaCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 326, 150, 22));

        txtkasir.setBackground(new java.awt.Color(93, 113, 119));
        txtkasir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtkasir.setBorder(null);
        txtkasir.setCaretColor(new java.awt.Color(93, 113, 119));
        txtkasir.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txtkasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkasirActionPerformed(evt);
            }
        });
        add(txtkasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 165, 150, 22));

        txtanggal.setBackground(new java.awt.Color(93, 113, 119));
        txtanggal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtanggal.setBorder(null);
        txtanggal.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txtanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtanggalActionPerformed(evt);
            }
        });
        add(txtanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 150, 20));

        jDateChooser1.setBackground(new java.awt.Color(93, 113, 119));
        jDateChooser1.setForeground(new java.awt.Color(93, 113, 119));
        add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 269, 160, 27));

        txkodebarang.setBackground(new java.awt.Color(93, 113, 119));
        txkodebarang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txkodebarang.setBorder(null);
        txkodebarang.setCaretColor(new java.awt.Color(93, 113, 119));
        txkodebarang.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(txkodebarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 545, 150, 22));

        btncari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncari.setText("Cari");
        btncari.setContentAreaFilled(false);
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, 70, 20));

        txnama1.setBackground(new java.awt.Color(93, 113, 119));
        txnama1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txnama1.setBorder(null);
        txnama1.setCaretColor(new java.awt.Color(93, 113, 119));
        txnama1.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txnama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txnama1ActionPerformed(evt);
            }
        });
        add(txnama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 602, 150, 22));

        txharga.setBackground(new java.awt.Color(93, 113, 119));
        txharga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txharga.setBorder(null);
        txharga.setCaretColor(new java.awt.Color(93, 113, 119));
        txharga.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txhargaActionPerformed(evt);
            }
        });
        add(txharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 665, 150, 22));

        txjumlah.setBackground(new java.awt.Color(93, 113, 119));
        txjumlah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txjumlah.setBorder(null);
        txjumlah.setCaretColor(new java.awt.Color(93, 113, 119));
        txjumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txjumlahMouseEntered(evt);
            }
        });
        txjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txjumlahActionPerformed(evt);
            }
        });
        txjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txjumlahKeyReleased(evt);
            }
        });
        add(txjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 727, 150, 22));

        totaldnt.setBackground(new java.awt.Color(93, 113, 119));
        totaldnt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totaldnt.setBorder(null);
        totaldnt.setCaretColor(new java.awt.Color(93, 113, 119));
        totaldnt.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        totaldnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaldntActionPerformed(evt);
            }
        });
        add(totaldnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 780, 150, 22));

        cbrasa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbrasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbrasaActionPerformed(evt);
            }
        });
        add(cbrasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 595, 160, 33));

        rsharga.setBackground(new java.awt.Color(93, 113, 119));
        rsharga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rsharga.setBorder(null);
        rsharga.setCaretColor(new java.awt.Color(93, 113, 119));
        rsharga.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        rsharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rshargaActionPerformed(evt);
            }
        });
        add(rsharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 670, 145, 22));

        rsjumlah.setBackground(new java.awt.Color(93, 113, 119));
        rsjumlah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rsjumlah.setBorder(null);
        rsjumlah.setCaretColor(new java.awt.Color(93, 113, 119));
        rsjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsjumlahActionPerformed(evt);
            }
        });
        rsjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rsjumlahKeyReleased(evt);
            }
        });
        add(rsjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 729, 145, 22));

        totalrs.setBackground(new java.awt.Color(93, 113, 119));
        totalrs.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalrs.setBorder(null);
        totalrs.setCaretColor(new java.awt.Color(93, 113, 119));
        totalrs.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        totalrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalrsActionPerformed(evt);
            }
        });
        add(totalrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 787, 140, 22));

        tbl_list.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_list);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 730, 220));

        btnsimpan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setContentAreaFilled(false);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 360, 80, -1));

        txtampil.setBackground(new java.awt.Color(51, 255, 0));
        txtampil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtampil.setText("Rp 0");
        txtampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtampilActionPerformed(evt);
            }
        });
        add(txtampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 250, 50));

        btntambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btntambah.setText("Tambah");
        btntambah.setContentAreaFilled(false);
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 364, 75, 20));

        btnhapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setContentAreaFilled(false);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1002, 358, 80, 30));

        btncetak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncetak.setText("Cetak");
        btncetak.setContentAreaFilled(false);
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(1125, 364, 80, 20));

        cmbstatuspembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunas", "Belum Lunas", " " }));
        cmbstatuspembayaran.setEnabled(false);
        cmbstatuspembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbstatuspembayaranActionPerformed(evt);
            }
        });
        add(cmbstatuspembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 433, 170, 40));

        txtotalbayar.setBackground(new java.awt.Color(93, 113, 119));
        txtotalbayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtotalbayar.setBorder(null);
        txtotalbayar.setCaretColor(new java.awt.Color(93, 113, 119));
        txtotalbayar.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txtotalbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtotalbayarActionPerformed(evt);
            }
        });
        add(txtotalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 436, 140, 22));

        txbayar.setBackground(new java.awt.Color(93, 113, 119));
        txbayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txbayar.setBorder(null);
        txbayar.setCaretColor(new java.awt.Color(93, 113, 119));
        txbayar.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txbayarActionPerformed(evt);
            }
        });
        txbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txbayarKeyReleased(evt);
            }
        });
        add(txbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 485, 140, 22));

        txkembalian.setBackground(new java.awt.Color(93, 113, 119));
        txkembalian.setBorder(null);
        txkembalian.setCaretColor(new java.awt.Color(93, 113, 119));
        txkembalian.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkembalianActionPerformed(evt);
            }
        });
        add(txkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 533, 140, 22));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("PELUNASAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 670, 130, 40));

        jLabel3.setFont(new java.awt.Font("DFPOP1-W9", 0, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\OneDrive\\Dokumen\\NetBeansProjects\\numnum\\src\\pct\\pesanan.png")); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 830));
    }// </editor-fold>//GEN-END:initComponents

    private void txtkasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkasirActionPerformed

    private void txtanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtanggalActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        cari1 c = new cari1();
        c.setVisible(true);
    }//GEN-LAST:event_btncariActionPerformed

    private void txnama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txnama1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txnama1ActionPerformed

    private void txjumlahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txjumlahMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahMouseEntered

    private void txjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahActionPerformed

    private void txjumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txjumlahKeyReleased
        // TODO add your handling code here:
        int hrg, jum, ttl;

        hrg = Integer.valueOf(txharga.getText());
        String input = txjumlah.getText().trim(); // Trim to remove leading/trailing whitespaces
        try {
            if (!input.isEmpty()) {
                jum = Integer.parseInt(input);
                if (jum < 0) {
                    JOptionPane.showMessageDialog(null, "Masukkan data angka");
                } else if (jum == 0) {
                    totaldnt.setText("");
                } else {
                    ttl = hrg * jum;
                    totaldnt.setText(String.valueOf(ttl));
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Masukkan data angka");
        }
    }//GEN-LAST:event_txjumlahKeyReleased

    private void totaldntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaldntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaldntActionPerformed

    private void cbrasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbrasaActionPerformed
      Connection c = koneksi.getKoneksi();
        try {
            
            try (Statement st = c.createStatement()) {
                ResultSet r = st.executeQuery("SELECT id_rasa, nama_rasa, harga FROM rasa");
                while (r.next()) {
                    String idRasa = r.getString("id_rasa"); // Ambil id_rasa
                    String namaRasa = r.getString("nama_rasa");
                    String harga = r.getString("harga");
                    idRasaToNamaRasa.put(idRasa, namaRasa); // Store id_rasa -> nama_rasa mapping
                    idRasaToHarga.put(idRasa, harga); // Store id_rasa -> harga mapping
                    cbrasa.addItem(namaRasa); // Masukkan nama_rasa ke combo box
                }
            }

            String selectedNamaRasa = cbrasa.getSelectedItem().toString(); // Ambil nama_rasa yang dipilih
            String selectedIdRasa = getKeyFromValue(idRasaToNamaRasa, selectedNamaRasa); // Dapatkan id_rasa dari nama_rasa yang dipilih

            String harga = idRasaToHarga.get(selectedIdRasa);
            if (harga != null) {
                rsharga.setText(harga);
            } else {
                // Reset textfield harga jika tidak ada hasil dari query
                rsharga.setText("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cbrasaActionPerformed

    private void rshargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rshargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rshargaActionPerformed

    private void rsjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsjumlahActionPerformed

    }//GEN-LAST:event_rsjumlahActionPerformed

    private void rsjumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rsjumlahKeyReleased
        // TODO add your handling code here:
        int hrgrasa, jumrasa, ttlrasa;

        hrgrasa = Integer.valueOf(rsharga.getText());
        String input = rsjumlah.getText().trim(); // Trim to remove leading/trailing whitespaces
        try {
            if (!input.isEmpty()) {
                jumrasa = Integer.parseInt(input);
                if (jumrasa < 0) {
                    JOptionPane.showMessageDialog(null, "Masukkan angka");
                } else if (jumrasa == 0) {
                    totalrs.setText("");
                } else {
                    ttlrasa = hrgrasa * jumrasa;
                    totalrs.setText(String.valueOf(ttlrasa));
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Masukkan angka");
        }
    }//GEN-LAST:event_rsjumlahKeyReleased

    private void totalrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalrsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalrsActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_list.getModel();
        String id_pesanan = txpesanan.getText();
        String total_harga = txtotalbayar.getText();
        String id_akun = txtkasir.getText();
        String total = txtotalbayar.getText();
        String bayar = txbayar.getText();
        String kembalian = txkembalian.getText();
        String nama_customer = txNamaCustomer.getText();
        Date tanggal_ambil = jDateChooser1.getDate();
        String status_pembayaran = cmbstatuspembayaran.getSelectedItem().toString();

        try {
            Connection c = koneksi.getKoneksi();
            String sql = "INSERT INTO pesanan (id_pesanan, id_karyawan, nama_customer, total_harga, bayar, kembalian, tanggal, tanggal_ambil, status_pembayaran) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id_pesanan);
            p.setString(2, id_akun);
            p.setString(3, nama_customer);
            p.setString(4, total_harga);
            p.setString(5, bayar);
            p.setString(6, kembalian);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            String tanggalString1 = outputFormat.format(tanggal_ambil);

            // Parse the formatted string back to a Date object
            Date parsedDate = outputFormat.parse(tanggalString1);

            // Set the Date object in the PreparedStatement
            p.setDate(7, new java.sql.Date(parsedDate.getTime()));
            p.setString(8, status_pembayaran);
            p.executeUpdate();
            p.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
    Connection c = koneksi.getKoneksi();
    int baris = tbl_list.getRowCount();

    String sql = "INSERT INTO detail_pesanan (id_pesanan, id_menu, jumlah, harga_donat, id_rasa, jumlah_rasa, total_rasa) VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement p = c.prepareStatement(sql);

    for (int i = 0; i < baris; i++) {
        p.setString(1, tbl_list.getValueAt(i, 0).toString());
        p.setString(2, tbl_list.getValueAt(i, 1).toString());
        p.setString(3, tbl_list.getValueAt(i, 2).toString());
        p.setString(4, tbl_list.getValueAt(i, 3).toString());
        p.setString(5, tbl_list.getValueAt(i, 4).toString());
        p.setString(6, tbl_list.getValueAt(i, 5).toString());
        p.setString(7, tbl_list.getValueAt(i, 6).toString());
        
        p.executeUpdate();
    }
    p.close();
} catch (Exception e) {
    System.out.println(e.getMessage());
}

        txtampil.setText("Rp 0");
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void txtampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtampilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtampilActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        tambahtransaksi();
        clear2();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_list.getModel();
        int row = tbl_list.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed

        String kode = txpesanan.getText();

        try {
            // Establish the database connection
            String DB = "jdbc:mysql://localhost/numnum";
            String user = "root";
            String pass = "";

            try (Connection conn = DriverManager.getConnection(DB, user, pass)) {
                // Prepare the SQL statement using PreparedStatement
                String sql = "SELECT * FROM pesanan WHERE id_pesanan = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, kode);

                    // Execute the query and get the result set
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // Retrieve data from the result set
                            String idTransaksi = resultSet.getString("id_pesanan");

                            // Set parameters for the report
                            HashMap<String, Object> parameters = new HashMap<>();
                            parameters.put("kode", kode);

                            // Compile and fill the report
                            String reportPath = "src/nota/pesan.jrxml";
                            JasperReport jrpt = JasperCompileManager.compileReport(reportPath);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jrpt, parameters, conn);

                            // Display the report using JasperViewer
                            JasperViewer.viewReport(jasperPrint, false);
                        } else {
                            JOptionPane.showMessageDialog(null, "No data found for id_pesanan: " + kode);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        clear();
        utama();
        autonumber();
    }//GEN-LAST:event_btncetakActionPerformed

    private void txtotalbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtotalbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtotalbayarActionPerformed

    private void txbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txbayarActionPerformed

    }//GEN-LAST:event_txbayarActionPerformed

    private void txbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyReleased
         // TODO add your handling code here:
        int total, bayar, kembalian;

        try {
            total = Integer.parseInt(txtotalbayar.getText());
            String pay = txbayar.getText().trim();

        if (!pay.isEmpty()) {
            bayar = Integer.parseInt(pay);
            kembalian = bayar - total;
            cmbstatuspembayaran.setSelectedItem("Lunas");
        if (kembalian >= 0) {
            txkembalian.setText(String.valueOf(kembalian));
            cmbstatuspembayaran.setSelectedItem("Lunas"); // Assuming you want to set it to "Lunas" if kembalian >= 0
        } else {
            txkembalian.setText("0");
            cmbstatuspembayaran.setSelectedItem("Belum Lunas"); // Assuming you want to set it to "Belum Lunas" if kembalian < 0
        }
        } else {
        JOptionPane.showMessageDialog(null, "Masukkan jumlah bayar");
        }
        } catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Masukkan angka yang valid");
    }

    }//GEN-LAST:event_txbayarKeyReleased

    private void txkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkembalianActionPerformed

    private void txhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txhargaActionPerformed

    private void txNamaCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNamaCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNamaCustomerActionPerformed

    private void cmbstatuspembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbstatuspembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbstatuspembayaranActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        test t = new test();
        t.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JComboBox<String> cbrasa;
    private javax.swing.JComboBox<String> cmbstatuspembayaran;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField rsharga;
    public static javax.swing.JTextField rsjumlah;
    private javax.swing.JTable tbl_list;
    public static javax.swing.JTextField totaldnt;
    public static javax.swing.JTextField totalrs;
    private javax.swing.JTextField txNamaCustomer;
    private javax.swing.JTextField txbayar;
    public static javax.swing.JTextField txharga;
    public static javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkembalian;
    public static javax.swing.JTextField txkodebarang;
    public static javax.swing.JTextField txnama1;
    private javax.swing.JTextField txpesanan;
    private javax.swing.JTextField txtampil;
    private javax.swing.JTextField txtanggal;
    public static javax.swing.JTextField txtkasir;
    private javax.swing.JTextField txtotalbayar;
    // End of variables declaration//GEN-END:variables

    
}
