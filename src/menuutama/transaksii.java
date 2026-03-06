package menuutama;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import java.text.DecimalFormat;
import java.util.HashMap;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class transaksii extends javax.swing.JPanel {

    String rasa = "";
    int hargatp;
    int hargars;
    int totalHarga;
    String Tanggal;
    
    private DefaultTableModel model; 
    
   

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
            String sql = "SELECT * FROM transaksi WHERE id_transaksi LIKE 'TR" + currentDate + "%' ORDER BY id_transaksi DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String lastId = r.getString("id_transaksi").substring(10); // Ambil bagian angka dari ID terakhir
                int nextNumber = Integer.parseInt(lastId) + 1;
                String formattedNumber = String.format("%04d", nextNumber); // Format nomor menjadi 4 digit dengan leading zeros
                txtransaksi.setText("TR" + currentDate + formattedNumber);
            } else {
                txtransaksi.setText("TR" + currentDate + "0001");
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
            txtransaksi.getText(),
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
        txtransaksi.setText("");
        txkodebarang.setText("");
        txjumlah.setText("");
        totaldnt.setText("");
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

    public void clear2() {
        txkodebarang.setText("");
        txnama1.setText("");
        txharga.setText("");
        txjumlah.setText("");
        totaldnt.setText("");
        rsharga.setText("");
        rsjumlah.setText("");
        totalrs.setText("");
        cbrasa.setSelectedItem(null);
    }

   
    public void tambahtransaksi() {
        int jumlah, harga, total, rs1, rs2;
        int totalrasa, totalfinal;

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

    public transaksii() {
        initComponents();
        String idAkun = SessionData.getIdAkun();
        jTextField1.setText(idAkun);
        model = new DefaultTableModel();
        tbl_list.setModel(model);
        tbl_list.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbl_list.getTableHeader().setOpaque(false);
        tbl_list.getTableHeader().setBackground(new Color(93, 113, 119));
        tbl_list.getTableHeader().setBackground(new Color(255, 255, 255));
        tbl_list.setRowHeight(25);
        model.addColumn("ID Transaksi");
        model.addColumn("Kode Donat");
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
        txtransaksi.requestFocus();
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

        btncari = new javax.swing.JButton();
        txtransaksi = new javax.swing.JTextField();
        txkodebarang = new javax.swing.JTextField();
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
        btncetak = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        txtotalbayar = new javax.swing.JTextField();
        txbayar = new javax.swing.JTextField();
        txkembalian = new javax.swing.JTextField();
        txtampil = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        txtanggal = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btncari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btncari.setText("Cari");
        btncari.setContentAreaFilled(false);
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 580, 80, 20));

        txtransaksi.setBackground(new java.awt.Color(93, 113, 119));
        txtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtransaksi.setCaretColor(new java.awt.Color(93, 113, 119));
        txtransaksi.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtransaksiActionPerformed(evt);
            }
        });
        add(txtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 150, 170, 40));

        txkodebarang.setBackground(new java.awt.Color(93, 113, 119));
        txkodebarang.setCaretColor(new java.awt.Color(93, 113, 119));
        txkodebarang.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(txkodebarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 570, 170, 42));

        txnama1.setBackground(new java.awt.Color(93, 113, 119));
        txnama1.setCaretColor(new java.awt.Color(93, 113, 119));
        txnama1.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txnama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txnama1ActionPerformed(evt);
            }
        });
        add(txnama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 635, 170, 42));

        txharga.setBackground(new java.awt.Color(93, 113, 119));
        txharga.setCaretColor(new java.awt.Color(93, 113, 119));
        txharga.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(txharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 697, 170, 42));

        txjumlah.setBackground(new java.awt.Color(93, 113, 119));
        txjumlah.setCaretColor(new java.awt.Color(93, 113, 119));
        txjumlah.setDisabledTextColor(new java.awt.Color(93, 113, 119));
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txjumlahKeyTyped(evt);
            }
        });
        add(txjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 754, 170, 42));

        totaldnt.setBackground(new java.awt.Color(93, 113, 119));
        totaldnt.setCaretColor(new java.awt.Color(93, 113, 119));
        totaldnt.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        totaldnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaldntActionPerformed(evt);
            }
        });
        totaldnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totaldntKeyTyped(evt);
            }
        });
        add(totaldnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 808, 170, 42));

        cbrasa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbrasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbrasaActionPerformed(evt);
            }
        });
        add(cbrasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 640, 170, 40));

        rsharga.setBackground(new java.awt.Color(93, 113, 119));
        rsharga.setCaretColor(new java.awt.Color(93, 113, 119));
        rsharga.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        rsharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rshargaActionPerformed(evt);
            }
        });
        add(rsharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 698, 170, 40));

        rsjumlah.setBackground(new java.awt.Color(93, 113, 119));
        rsjumlah.setCaretColor(new java.awt.Color(93, 113, 119));
        rsjumlah.setDisabledTextColor(new java.awt.Color(93, 113, 119));
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
        add(rsjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 760, 170, 40));

        totalrs.setBackground(new java.awt.Color(93, 113, 119));
        totalrs.setCaretColor(new java.awt.Color(93, 113, 119));
        totalrs.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        totalrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalrsActionPerformed(evt);
            }
        });
        add(totalrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 817, 170, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 760, 220));

        btnsimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setContentAreaFilled(false);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 90, 30));

        btncetak.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btncetak.setText("Cetak");
        btncetak.setContentAreaFilled(false);
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 400, 75, 30));

        btnhapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setContentAreaFilled(false);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 400, 80, 30));

        txtotalbayar.setBackground(new java.awt.Color(93, 113, 119));
        txtotalbayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtotalbayar.setCaretColor(new java.awt.Color(93, 113, 119));
        txtotalbayar.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txtotalbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtotalbayarActionPerformed(evt);
            }
        });
        add(txtotalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 467, 170, 40));

        txbayar.setBackground(new java.awt.Color(93, 113, 119));
        txbayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
        add(txbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 517, 170, 40));

        txkembalian.setBackground(new java.awt.Color(93, 113, 119));
        txkembalian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txkembalian.setCaretColor(new java.awt.Color(93, 113, 119));
        txkembalian.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkembalianActionPerformed(evt);
            }
        });
        add(txkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 565, 170, 40));

        txtampil.setBackground(new java.awt.Color(51, 255, 0));
        txtampil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtampil.setCaretColor(new java.awt.Color(51, 255, 0));
        txtampil.setDisabledTextColor(new java.awt.Color(51, 255, 0));
        txtampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtampilActionPerformed(evt);
            }
        });
        add(txtampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 260, 60));

        btntambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntambah.setText("Tambah");
        btntambah.setContentAreaFilled(false);
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1102, 400, 90, 30));

        txtanggal.setBackground(new java.awt.Color(93, 113, 119));
        txtanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtanggal.setCaretColor(new java.awt.Color(93, 113, 119));
        txtanggal.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        txtanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtanggalActionPerformed(evt);
            }
        });
        add(txtanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 217, 170, 40));

        jTextField1.setBackground(new java.awt.Color(93, 113, 119));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 280, 170, 40));

        jLabel3.setFont(new java.awt.Font("DFPOP1-W9", 0, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\OneDrive\\Dokumen\\NetBeansProjects\\numnum\\src\\pct\\transaksi.png")); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        tambahtransaksi();
    }//GEN-LAST:event_btntambahActionPerformed

    private void txtampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtampilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtampilActionPerformed

    private void txkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkembalianActionPerformed

    private void txbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyReleased
        // TODO add your handling code here:
        int total, bayar, kembalian;

        try {
            total = Integer.parseInt(txtotalbayar.getText());
            String pay = txbayar.getText().trim();

            if (!pay.isEmpty()) {
                bayar = Integer.parseInt(pay);
                kembalian = bayar - total;
                txkembalian.setText(String.valueOf(kembalian));
            } else {
                JOptionPane.showMessageDialog(null, "Masukkan jumlah bayar");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Masukkan angka");
        }
    }//GEN-LAST:event_txbayarKeyReleased

    private void txbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txbayarActionPerformed

    }//GEN-LAST:event_txbayarActionPerformed

    private void txtotalbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtotalbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtotalbayarActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_list.getModel();
        int row = tbl_list.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        
        String kodee = txtransaksi.getText();
        try {
            // Establish the database connection
            String DB = "jdbc:mysql://localhost/numnum";
            String user = "root";
            String pass = "";

            try (Connection conn = DriverManager.getConnection(DB, user, pass)) {
                // Prepare the SQL statement using PreparedStatement
                String sql = "SELECT * FROM transaksi WHERE id_transaksi = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, kodee);

                    // Execute the query and get the result set
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // Retrieve data from the result set
                            String idTransaksi = resultSet.getString("id_transaksi");

                            // Set parameters for the report
                            HashMap<String, Object> parameters = new HashMap<>();
                            parameters.put("kode", kodee);

                            // Compile and fill the report
                            String reportPath = "src/nota/note.jrxml";
                            JasperReport jrpt = JasperCompileManager.compileReport(reportPath);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jrpt, parameters, conn);

                            // Display the report using JasperViewer
                            JasperViewer.viewReport(jasperPrint, false);
                        } else {
                            JOptionPane.showMessageDialog(null, "No data found for id_transaksi: " + kodee);
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

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_list.getModel();

        String id_transaksi = txtransaksi.getText();
        String total_harga = txtotalbayar.getText();
        String id_akun = jTextField1.getText();
        String total = txtotalbayar.getText();
        String bayar = txbayar.getText();
        String kembalian = txkembalian.getText();

        try {

            Connection c = koneksi.getKoneksi();
            String sql = "INSERT INTO transaksi (id_transaksi, tanggal, id_karyawan, total_harga, bayar, kembalian) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id_transaksi);
            p.setString(2, id_akun);
            p.setString(3, total_harga);
            p.setString(4, bayar);
            p.setString(5, kembalian);
            p.executeUpdate();
            p.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection c = koneksi.getKoneksi();
            int baris = tbl_list.getRowCount();

            for (int i = 0; i < baris; i++) {
                String sql = "INSERT INTO detail_transaksi (id_transaksi, id_menu, qty_donat, harga_total, id_rasa, jumlahrs, totalrs) VALUES ('" + tbl_list.getValueAt(i, 0) + "','" + tbl_list.getValueAt(i, 1)
                + "','" + tbl_list.getValueAt(i, 2) + "','" + tbl_list.getValueAt(i, 3) + "','" + tbl_list.getValueAt(i, 4) + "','" + tbl_list.getValueAt(i, 5) + "','" + tbl_list.getValueAt(i, 6) + "')";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtampil.setText("Rp 0");
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void totalrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalrsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalrsActionPerformed

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

    private void rsjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rsjumlahActionPerformed

    private void rshargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rshargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rshargaActionPerformed

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

    private void totaldntKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totaldntKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totaldntKeyTyped

    private void totaldntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaldntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaldntActionPerformed

    private void txjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txjumlahKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahKeyTyped

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

    private void txjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txjumlahActionPerformed

    }//GEN-LAST:event_txjumlahActionPerformed

    private void txjumlahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txjumlahMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txjumlahMouseEntered

    private void txnama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txnama1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txnama1ActionPerformed

    private void txtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtransaksiActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        cari c = new cari();
        c.setVisible(true);
    }//GEN-LAST:event_btncariActionPerformed

    private void txtanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtanggalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JComboBox<String> cbrasa;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField rsharga;
    public static javax.swing.JTextField rsjumlah;
    private javax.swing.JTable tbl_list;
    public static javax.swing.JTextField totaldnt;
    public static javax.swing.JTextField totalrs;
    private javax.swing.JTextField txbayar;
    public static javax.swing.JTextField txharga;
    public static javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkembalian;
    public static javax.swing.JTextField txkodebarang;
    public static javax.swing.JTextField txnama1;
    private javax.swing.JTextField txtampil;
    private javax.swing.JTextField txtanggal;
    private javax.swing.JTextField txtotalbayar;
    private javax.swing.JTextField txtransaksi;
    // End of variables declaration//GEN-END:variables
}
