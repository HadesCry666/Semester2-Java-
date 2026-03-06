
package menuutama;

import java.io.BufferedWriter;
import javax.swing.JTable;
import java.io.FileWriter;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.text.NumberFormat;
import java.util.Locale; 

public class laporan extends javax.swing.JPanel {
    
    
   private DefaultTableModel model;
    private DefaultTableModel model1;
    
    Date date1;
    Date date2;
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "SELECT DATE(tanggal) AS tanggal, SUM(total_harga) AS total_harga_harian FROM transaksi GROUP BY DATE(tanggal);";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[4];
                o [0] = r.getString("tanggal");
                o [1] = r.getString("total_harga_harian");
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    
    }
    
    private void kosong(){
        masukk.setText("0");
        keluar.setText("0");
        profit.setText("0");
    }
    


    public void masuk() {
        int jumlahbaris = jTable1.getRowCount();
        int total = 0;
        for (int i = 0; i < jumlahbaris; i++) {
            total += Integer.parseInt(jTable1.getValueAt(i, 1).toString());
        }

        // Format uang tanpa desimal dan tanpa simbol mata uang
        DecimalFormat formatRibuan = new DecimalFormat("Rp #,###");
        String formattedTotal = formatRibuan.format(total);

        masukk.setText(formattedTotal);
    }

    public void keluar() {
        int jumlahbaris = jTable2.getRowCount();
        int total = 0;
        for (int i = 0; i < jumlahbaris; i++) {
            total += Integer.parseInt(jTable2.getValueAt(i, 3).toString());
        }

        // Format uang tanpa desimal dan tanpa simbol mata uang
        DecimalFormat formatRibuan = new DecimalFormat("Rp #,###");
        String formattedTotal = formatRibuan.format(total);

        keluar.setText(formattedTotal);
    }

    private void untung() {
        try {
            int masukin = parseCurrencyInput(masukk.getText());
            int keluarin = parseCurrencyInput(keluar.getText());
            int untungg;

            if (keluarin > masukin) {
                JOptionPane.showMessageDialog(null, "Tidak ada keuntungan");
            } else {
                untungg = masukin - keluarin;

                // Format uang tanpa desimal dan tanpa simbol mata uang
                DecimalFormat formatRibuan = new DecimalFormat("Rp #,###");
                String formattedUntung = formatRibuan.format(untungg);

                profit.setText(formattedUntung);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid. Masukkan angka saja.");
        }
    }

    // Fungsi untuk membersihkan dan mengkonversi format uang ke dalam nilai angka
    private int parseCurrencyInput(String input) {
        String cleanInput = input.replaceAll("[^0-9]", "");
        return Integer.parseInt(cleanInput);
    }

    
    private void pemasukan() {
    try {
        date1 = tanggalss1.getDate();
        date2 = tanggalss2.getDate();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal = outputFormat.format(date1);
        String tanggal1 = outputFormat.format(date2);

        // Parse the formatted string back to a Date object
        Date parsedDate = outputFormat.parse(tanggal);
        Date parsedDate1 = outputFormat.parse(tanggal1);

        // Convert Java Date objects to SQL Date objects
        java.sql.Date sqlDate1 = new java.sql.Date(parsedDate.getTime());
        java.sql.Date sqlDate2 = new java.sql.Date(parsedDate1.getTime());

        String sql = "SELECT tanggal, total_harga, 'pesanan' AS jenis_data FROM pesanan WHERE tanggal BETWEEN ? AND ?\n" +
                     "UNION \n" +
                     "SELECT tanggal, total_harga, 'transaksi' AS jenis_data FROM transaksi WHERE tanggal BETWEEN ? AND ?;";
        Connection conn = koneksi.getKoneksi();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDate(1, sqlDate1);
            pst.setDate(2, sqlDate2);
            pst.setDate(3, sqlDate1);  // Add these lines
            pst.setDate(4, sqlDate2);  // Add these lines

            ResultSet rs = pst.executeQuery();

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();

            while (rs.next()) {
                Date tanggals = rs.getDate("tanggal");
                int totalHarga = rs.getInt("total_harga");
                String jenisData = rs.getString("jenis_data");
                Object[] rowData = {tanggals, totalHarga, jenisData};  // Change to tanggals
                model.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    } catch (ParseException ex) {
        Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
    }
}

  
    
    private void pengeluaran() {
        try {
        date1 = tanggalss1.getDate();
        date2 = tanggalss2.getDate();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        String tanggalString = outputFormat.format(date1);
        String tanggalString1 = outputFormat.format(date2);
        
        Date parsedDate = outputFormat.parse(tanggalString);
        Date parsedDate1 = outputFormat.parse(tanggalString1);

        java.sql.Date sqlDate1 = new java.sql.Date(parsedDate.getTime());
        java.sql.Date sqlDate2 = new java.sql.Date(parsedDate1.getTime());

        String sql = "SELECT * FROM pengeluaran WHERE tanggal BETWEEN ? AND ? GROUP BY tanggal";

        Connection conn = koneksi.getKoneksi();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDate(1, sqlDate1);
            pst.setDate(2, sqlDate2);

            ResultSet rs = pst.executeQuery();

            model1.getDataVector().removeAllElements();
            model1.fireTableDataChanged();

            while (rs.next()) {
           
            String kode = rs.getString("kode_pengeluaran");
            String tanggal = rs.getString("tanggal");
            String ket = rs.getString("keterangan");
            int totalHarga = rs.getInt("nominal");
            Object[] rowData = {kode, tanggal, ket, totalHarga, }; 
            model1.addRow(rowData);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle the exception appropriately
    }
}       catch (ParseException ex) {
            Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public laporan() {
        initComponents();
         model = new DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("Tanggal");
        model.addColumn("Total");
        model.addColumn("Jenis Data");
        
        model1 = new DefaultTableModel();
        jTable2.setModel(model1);
        model1.addColumn("Kode");
        model1.addColumn("Tanggal");
        model1.addColumn("Keterangan");
        model1.addColumn("Total");
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tanggalss1 = new com.toedter.calendar.JDateChooser();
        tanggalss2 = new com.toedter.calendar.JDateChooser();
        btn_pilih = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        masukk = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        keluar = new javax.swing.JTextField();
        profit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        csv = new javax.swing.JButton();
        xlsx = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tanggalss1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tanggalss1KeyTyped(evt);
            }
        });
        add(tanggalss1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 130, 30));
        add(tanggalss2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 130, 30));

        btn_pilih.setContentAreaFilled(false);
        btn_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihActionPerformed(evt);
            }
        });
        add(btn_pilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 80, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Pemasukan");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

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
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 750, 170));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, 750, 170));

        masukk.setBackground(new java.awt.Color(93, 113, 119));
        masukk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        masukk.setBorder(null);
        masukk.setCaretColor(new java.awt.Color(93, 113, 119));
        masukk.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(masukk, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 700, 170, 22));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Pengeluaran");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 110, -1));

        keluar.setBackground(new java.awt.Color(93, 113, 119));
        keluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        keluar.setBorder(null);
        keluar.setCaretColor(new java.awt.Color(93, 113, 119));
        keluar.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        add(keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 700, 170, 20));

        profit.setBackground(new java.awt.Color(93, 113, 119));
        profit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        profit.setBorder(null);
        profit.setCaretColor(new java.awt.Color(93, 113, 119));
        profit.setDisabledTextColor(new java.awt.Color(93, 113, 119));
        profit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profitActionPerformed(evt);
            }
        });
        add(profit, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 769, 170, 20));

        jLabel3.setFont(new java.awt.Font("DFPOP1-W9", 0, 18)); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        csv.setText("CSV");
        csv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvActionPerformed(evt);
            }
        });
        add(csv, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 760, 100, 40));

        xlsx.setText("XLSX");
        xlsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xlsxActionPerformed(evt);
            }
        });
        add(xlsx, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 760, 100, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\OneDrive\\Dokumen\\NetBeansProjects\\numnum\\src\\pct\\lporanfix.png")); // NOI18N
        jLabel1.setText("jLabel1");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 840));
    }// </editor-fold>//GEN-END:initComponents

    private void tanggalss1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tanggalss1KeyTyped
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_tanggalss1KeyTyped

    private void btn_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihActionPerformed
        // TODO add your handling code here:
        kosong();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        pemasukan();
        pengeluaran();
        masuk();
        keluar();
        untung();
    }//GEN-LAST:event_btn_pilihActionPerformed

    private void profitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profitActionPerformed
    
    private void csvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvActionPerformed
        String csvFilePath = "D:\\SI\\laporan.csv";

         try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Menulis header
            String header = "TANGGAL,HARGA,KETERANGAN";
            fileWriter.write(header);
            fileWriter.newLine();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Menulis data dari jTable1
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String tanggal =  formatDate(jTable1.getValueAt(i, 0), dateFormat);
                String harga = jTable1.getValueAt(i, 1).toString();
                String jenisTransaksi = jTable1.getValueAt(i, 2).toString();
                String line = String.format("%s,%s,%s", tanggal, harga, jenisTransaksi);
                fileWriter.write(line);
                fileWriter.newLine();
            }

            // Menulis data dari jTable2
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                String tanggal = formatDate(jTable2.getValueAt(i, 1), dateFormat);
                String harga = jTable2.getValueAt(i, 3).toString();
                String jenisTransaksi = jTable2.getValueAt(i, 2).toString();
                String line = String.format("%s,%s,%s", tanggal, harga, jenisTransaksi);
                fileWriter.write(line);
                fileWriter.newLine();
            }

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke CSV.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menulis file: " + e.getMessage());
        }
    }//GEN-LAST:event_csvActionPerformed

    private void xlsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xlsxActionPerformed
       String csvFilePath = "D:\\SI\\laporan1.xlsx";

         try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Menulis header
            String header = "TANGGAL,HARGA,KETERANGAN";
            fileWriter.write(header);
            fileWriter.newLine();

            // Menulis data dari jTable1
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String tanggal = jTable1.getValueAt(i, 0).toString();
                String harga = jTable1.getValueAt(i, 1).toString();
                String jenisTransaksi = jTable1.getValueAt(i, 2).toString();
                String line = String.format("%s,%s,%s", tanggal, harga, jenisTransaksi);
                fileWriter.write(line);
                fileWriter.newLine();
            }

            // Menulis data dari jTable2
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                String tanggal = jTable2.getValueAt(i, 1).toString();
                String harga = jTable2.getValueAt(i, 3).toString();
                String jenisTransaksi = jTable2.getValueAt(i, 2).toString();
                String line = String.format("%s,%s,%s", tanggal, harga, jenisTransaksi);
                fileWriter.write(line);
                fileWriter.newLine();
            }

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke XLSX.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menulis file: " + e.getMessage());
        }
    }//GEN-LAST:event_xlsxActionPerformed
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pilih;
    private javax.swing.JButton csv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField keluar;
    private javax.swing.JTextField masukk;
    private javax.swing.JTextField profit;
    private com.toedter.calendar.JDateChooser tanggalss1;
    private com.toedter.calendar.JDateChooser tanggalss2;
    private javax.swing.JButton xlsx;
    // End of variables declaration//GEN-END:variables

    private static String formatDate(Object dateObj, SimpleDateFormat dateFormat) {
        if (dateObj instanceof Date) {
            return dateFormat.format((Date) dateObj);
        } else {
            return dateObj.toString(); // Assume it's already a formatted string if not a Date
        }
    }
}
