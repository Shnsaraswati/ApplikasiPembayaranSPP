/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author Global Store
 */
import java.sql.Connection;

/**
 * membuat interface dengan nama interface_crud agar bisa di implementasikan ke class crud di package koneksi
 */
public interface interface_crud {
    /**
     * didalam interface_crud ada 2 method yang akan digunakan di dalam class crud
     * 1. method void update() disana terdapat 4 parameter
     * 2. method void delete() disana terdapat 3 parameter
     * 
     */
    public void update(String nama_table, String data, String id, Connection conn);
    public void delete(String nama_table, String id, Connection conn);
}
