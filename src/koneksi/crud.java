/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author Global Store
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/*
    * class crud ini adalah class inheritance dari class koneksi dimana class ini akan digunakan untuk update dan delete
*/

public class crud extends koneksi{
    /**
     * function ini digunakan untuk update sebuah table yang ada didatabase dengan parameter :
     * String nama_table : diisi dengan nama table yang mau diisi
     * String data : digunakan untuk mengupdate semester dengan data yang diinginkan
     * String id : untuk mengambil sebuah id untuk diberikan kepada id_pembayaran
     * Connection conn : untuk membuat sebuah query dan mengeksekusinya
     */
    public void update(String nama_table, String data, String id, Connection conn){
        String query = "UPDATE "+nama_table+" set semester=? WHERE id_pembayaran=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, data);
            stmt.setString(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI UPDATE!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
    
    /**
     * function delete ini digunakan untuk menghapus sebuah record dari table yang ditentukan / disini contohnya tbl pembayaran
     * String nama_table : untuk mengisi nama_table mana yang mau di hapus recordnya
     * String id : adalah id yang akan diambil nanti untuk di cocokkan di id_pembayaran yang nantinya akan dihapus
     * Connection conn : koneksi untuk membuat query dan mengeksekusinya
     */
    
    public void delete(String nama_table, String id, Connection conn) {
        String query = "DELETE FROM "+nama_table+" where id_pembayaran=?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI HAPUS!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
}
