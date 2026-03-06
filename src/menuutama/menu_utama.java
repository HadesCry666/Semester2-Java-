package menuutama;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class menu_utama extends javax.swing.JFrame {

    public menu_utama() {
        initComponents();
        execute();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_navbar = new javax.swing.JPanel();
        pn_sidebar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        pn_content = new javax.swing.JPanel();
        pn_utama = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1535, 1000));
        setMinimumSize(new java.awt.Dimension(1535, 1000));
        setPreferredSize(new java.awt.Dimension(1535, 1000));
        setSize(new java.awt.Dimension(1535, 1000));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pn_navbar.setBackground(new java.awt.Color(93, 119, 114));

        javax.swing.GroupLayout pn_navbarLayout = new javax.swing.GroupLayout(pn_navbar);
        pn_navbar.setLayout(pn_navbarLayout);
        pn_navbarLayout.setHorizontalGroup(
            pn_navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1827, Short.MAX_VALUE)
        );
        pn_navbarLayout.setVerticalGroup(
            pn_navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(pn_navbar, java.awt.BorderLayout.PAGE_START);

        pn_sidebar.setBackground(new java.awt.Color(255, 255, 255));
        pn_sidebar.setPreferredSize(new java.awt.Dimension(280, 559));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(255, 255, 255));
        menus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout pn_sidebarLayout = new javax.swing.GroupLayout(pn_sidebar);
        pn_sidebar.setLayout(pn_sidebarLayout);
        pn_sidebarLayout.setHorizontalGroup(
            pn_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        pn_sidebarLayout.setVerticalGroup(
            pn_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
        );

        getContentPane().add(pn_sidebar, java.awt.BorderLayout.LINE_START);

        pn_content.setBackground(new java.awt.Color(255, 255, 255));
        pn_content.setMaximumSize(new java.awt.Dimension(1240, 885));
        pn_content.setMinimumSize(new java.awt.Dimension(1240, 885));

        pn_utama.setBackground(new java.awt.Color(102, 255, 102));
        pn_utama.setMaximumSize(new java.awt.Dimension(1235, 885));
        pn_utama.setMinimumSize(new java.awt.Dimension(1235, 885));
        pn_utama.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pn_contentLayout = new javax.swing.GroupLayout(pn_content);
        pn_content.setLayout(pn_contentLayout);
        pn_contentLayout.setHorizontalGroup(
            pn_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_contentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 1534, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_contentLayout.setVerticalGroup(
            pn_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
        );

        getContentPane().add(pn_content, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        pn_utama.add(new dashboard());
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel pn_content;
    private javax.swing.JPanel pn_navbar;
    private javax.swing.JPanel pn_sidebar;
    private javax.swing.JPanel pn_utama;
    // End of variables declaration//GEN-END:variables

   private void execute() {
        ImageIcon iconMaster        = createImageIcon("/pct/dashboard.png", "dashboard");
        ImageIcon iconMenu          = createImageIcon("/pct/menu.png", "menu");
        ImageIcon iconTransaksi     = createImageIcon("/pct/money-transfer.png", "money-transfer");
        ImageIcon iconLaporan       = createImageIcon("/pct/report.png", "report");
        ImageIcon iconPengeluaran   = createImageIcon("/pct/expenses.png", "expenses");
        ImageIcon iconUser          = createImageIcon("/pct/process.png", "process");
        ImageIcon iconPesanan       = createImageIcon("/pct/purchase-order.png", "purchase-order");
        ImageIcon iconlogout        = createImageIcon("/pct/logout.png", "logout");


        menu_item transaksi1 = new menu_item(null, true, iconTransaksi, "Transaksi", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new transaksii());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
    
        
        menu_item laporan1      = new menu_item(null,true,iconLaporan,"Laporan", new ActionListener(){
        public void actionPerformed(ActionEvent e){
            pn_utama.removeAll();
            pn_utama.add(new laporan());
            pn_utama.repaint();
            pn_utama.revalidate();
        }   
   });  
        
        menu_item pengeluaran      = new menu_item(null,true,iconPengeluaran,"Pengeluaran", new ActionListener(){
        public void actionPerformed(ActionEvent e){
            pn_utama.removeAll();
            pn_utama.add(new pengeluaran());
            pn_utama.repaint();
            pn_utama.revalidate();
        }   
   });  
        
        menu_item pesanan       = new menu_item(null,true,iconPesanan,"Pesanan",new ActionListener(){
        public void actionPerformed(ActionEvent e){
            pn_utama.removeAll();
            pn_utama.add(new Pesanan());
            pn_utama.repaint();
            pn_utama.revalidate();
        }   
   });  
        
        menu_item menuu       = new menu_item(null,true,iconMenu,"Menu",new ActionListener(){
        public void actionPerformed(ActionEvent e){
            pn_utama.removeAll();
            pn_utama.add(new menudonat());
            pn_utama.repaint();
            pn_utama.revalidate();
        }   
   });  
        
        menu_item rasa       = new menu_item(null,true,iconMenu,"Rasa",new ActionListener(){
        public void actionPerformed(ActionEvent e){
            pn_utama.removeAll();
            pn_utama.add(new rasa());
            pn_utama.repaint();
            pn_utama.revalidate();
        }   
   });  
        
        menu_item logout       = new menu_item(iconlogout, false, null, "Logout", new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int jawab = JOptionPane.showConfirmDialog(null, "Apakah anda Ingin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        // If the user confirms (YES_OPTION), proceed with logout
        if (jawab == JOptionPane.YES_OPTION) {
            // Clear the main panel and switch to the login view
            removeAll();
            login l = new login();
            l.setVisible(true);
            dispose();
        }
        }   
   });  
        
        menu_item Dashboard       = new menu_item(iconMaster, false, null, "Dashboard", new ActionListener(){
        public void actionPerformed(ActionEvent e){
            pn_utama.removeAll();
            pn_utama.add(new dashboard());
            pn_utama.repaint();
            pn_utama.revalidate();
        }   
   });  
       
        menu_item Transaksi     = new menu_item(iconTransaksi, false, null, "Transaksi", null, transaksi1,pesanan);
        menu_item Menu          = new menu_item(iconMenu, false, null, "Menu", null, menuu, rasa);
        
        menu_item user          = new menu_item(iconUser, false, null, "Data User", new ActionListener(){
            public void actionPerformed(ActionEvent e){
            pn_utama.removeAll();
            pn_utama.add(new user());
            pn_utama.repaint();
            pn_utama.revalidate();
        }
        });
        menu_item Report        = new menu_item(iconLaporan, false, null, "Laporan", null, laporan1,pengeluaran);
        
        if (login.status.equals("admin")){
            addMenu(Dashboard, Transaksi, Menu, user, Report, logout);
        } else {
            addMenu(Dashboard, Transaksi, Menu, logout);
        }
   }

    
    private void addMenu (menu_item... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<menu_item> subMenu = menu[i].getsubMenu();
            for (menu_item m : subMenu) {
                addMenu (m);
            }
        }
        menus.revalidate();
    }

     private ImageIcon createImageIcon(String path, String description) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + description + " at " + path);
            return null;
        }
    }
}
