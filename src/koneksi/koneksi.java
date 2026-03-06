/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class koneksi {

    private static Connection koneksi;
       
   public static Connection getKoneksi(){
       if (koneksi == null){
           try {
               String url = "jdbc:mysql://localhost:3306/numnum";
               String user = "root";
               String password = "";
               DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
               koneksi = DriverManager.getConnection(url, user, password);
               System.out.println("Koneksi berhasil");
           } catch (Exception e) {
               System.out.println("Koneksi gagal");
           }
       }
       return koneksi;
   }
   
   public static void main(String args[]){
       getKoneksi();
   }
    
}


