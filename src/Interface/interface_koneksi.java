package Interface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Global Store
 */

import java.sql.Connection;

/**
 * membuat interface_koneksi untuk diimplementasikan kepada class koneksi yang ada dipackage koneksi
 */
public interface interface_koneksi {
    // didalam interface_koneksi terdapat method connect yang mereturnkan nilai Connection
   public Connection connect();
}
