/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

// import interface untuk implements
import Interface.interface_koneksi;
/**
 *
 * @author Global Store
 */

/**
 * didalam class koneksi dia implements interface_koneksi yang maksudnya
 * class koneksi ini dia harus mengimplementasikan method method yang telah di buat di interface_koneksi
 * seperti dibawah di interface_koneksi dia mempunya method dengan nama connect() dengan return connection
 * dan di class koneksi dia juga harus punya method connect() dengan return nilai Connection
 */
public class koneksi implements interface_koneksi {
     private Connection koneksi; // membuat variabel koneksi untuk mengambil koneksi
     
     // membuat function dengan nama connect untuk mengkoneksikan database 
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); // menghubungkan ke driver jdbc
            System.out.println("berhasil konek");  // jika berhaisl menghubungkan akan muncul berhasil konek
        }catch(ClassNotFoundException ex){
            System.out.println("gagal konek"+ex);
        }
        String url = "jdbc:mysql://localhost/pembayaranspp"; // menghubunkan ke dabatase mysql dengan nama database pembayaranspp
        try{
            koneksi = (Connection)DriverManager.getConnection(url,"root","");
        }catch(SQLException ex){
            System.out.println("gagal konek db"+ex); // jika gagal terhubung maka akan mengeluarkan ouput error
        }
        return koneksi;
    }
}
