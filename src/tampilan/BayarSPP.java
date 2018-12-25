/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import koneksi.koneksi;
import Interface.interface_BayarSPP;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author Global Store
 */
public class BayarSPP extends javax.swing.JFrame implements interface_BayarSPP {

    public Connection conn = new koneksi().connect();
    ArrayList<String> data = new ArrayList<>();
    int spp = 150000;
    String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    ArrayList<String> bulanBayar = new ArrayList<>();
    
    public void TotalBayar() {
        String query = "SELECT (SUM(pembayaran_untuk_berapa_bulan) * SUM(uangspp)) as totalbayar FROM pembayaran where NIS=" + comboboxNIS.getSelectedItem().toString() + "";
        String totalbayar = "";
        try {
            Statement stmt = conn.createStatement();
            ResultSet hasil = stmt.executeQuery(query);
            if (hasil.next()) {
                if (hasil.getInt("totalbayar") == 0) {
                    jLabelTotalBayar.setText(hasil.getString("0"));
                }
            } else {
                jLabelTotalBayar.setText(hasil.getString("totalbayar"));
            }
        } catch (Exception e) {
        }

    }

    public void uangkembali() {
        String query = "SELECT (SUM(uangbayar) - SUM(totalbayar)) as uangkembali FROM pembayaran where NIS=" + comboboxNIS.getSelectedItem().toString() + "";
        String uangkembali = "";
    }

    public void getNIS() {
        String query = "SELECT NIS from data_murid";
        try {
            Statement stmt = conn.createStatement();
            ResultSet hasil = stmt.executeQuery(query);
            while (hasil.next()) {
                comboboxNIS.addItem(hasil.getString("NIS"));
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public void findSiswa() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy"); //membuat format untuk tanggal
        Date date = new Date(); // membuat date untuk mengambil tanggal hari ini
        String query = "SELECT * from data_murid where NIS=" + comboboxNIS.getSelectedItem().toString() + "";
        try {
            Statement stmt = conn.createStatement();
            ResultSet hasil = stmt.executeQuery(query);
            if (hasil.next()) {
                jLabelNama.setText(hasil.getString("nama"));
                txtKelas.setText(hasil.getString("kelas"));
                tanggalBayar.setText(dateFormat.format(date).toString());
            }
        } catch (Exception e) {
        }
    }

    public void getBulan() {
        String query = "SELECT * from bulan";
        try {
            Statement stmt = conn.createStatement();
            ResultSet hasil = stmt.executeQuery(query);
            while (hasil.next()) {
                CbBulanBayar.addItem(hasil.getString("nama_bulan"));
            }
        } catch (Exception e) {
        }
    }

    public void getBelum() {
        String query = "SELECT bulan from pembayaran where NIS=" + comboboxNIS.getSelectedItem().toString() + " AND semester='" + cbSemester.getSelectedItem().toString() + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet hasil = stmt.executeQuery(query);
            CbBulanBayar.removeAllItems();
            bulanBayar.clear();
            while (hasil.next()) {                
                bulanBayar.add(hasil.getString("bulan"));
            }
            int x = 0;
            for(int i=x; i<bulanBayar.size(); i++)
            {
                if (bulanBayar.isEmpty()) {
                    System.out.println("Kosong");
                }
                else
                {
                    if(bulan[i].equals(bulanBayar.get(i)))
                    {
                        System.out.println("Sama");
                    }
                    else if(bulan[i].equals(bulanBayar.lastIndexOf(i)))
                    {
                        System.out.println("Beda");
                    }
                }
                x++;
            }
            
            for (int i = x; i < bulan.length; i++) {
                CbBulanBayar.addItem(bulan[i]);
            }
            
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public void getBelumBayar() {
        String query = "SELECT nama_bulan FROM bulan, pembayaran pb WHERE pb.NIS = " + comboboxNIS.getSelectedItem().toString() + " AND pb.Semester = '" + cbSemester.getSelectedItem().toString() + "' AND pb.tanggal LIKE '%/2018%' AND pb.bulan != bulan.nama_bulan";
        try {
            Statement stmt = conn.createStatement();
            ResultSet hasil = stmt.executeQuery(query);
            CbBulanBayar.removeAllItems();
            if (!hasil.next()) {
//                System.out.println("Tidak ada bulan");
                getBulan();
            } else {
//                System.out.println(hasil.getString("nama_bulan"));
                do {
                    CbBulanBayar.addItem(hasil.getString("nama_bulan"));
                } while (hasil.next());
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public BayarSPP() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        comboboxNIS.removeAllItems();
        comboboxNIS.addItem("Pilih NIS : ");
        getNIS();
        TotalBayar();
        uangkembali();

        cbSemester.removeAllItems();
        cbSemester.addItem("Ganjil");
        cbSemester.addItem("Genap");

        CbBulanBayar.removeAllItems();
        getBulan();

        comboboxNIS.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    findSiswa();
                    getBelum();
                }
            }
        });
        cbSemester.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    getBelumBayar();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtKelas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbSemester = new javax.swing.JComboBox<>();
        txtuangbayar = new javax.swing.JTextField();
        jLabelNama = new javax.swing.JLabel();
        jButtonexit = new javax.swing.JButton();
        jButtonkembalikemenu = new javax.swing.JButton();
        jButtonbayar = new javax.swing.JButton();
        lblUangkembali = new javax.swing.JLabel();
        CbBulanBayar = new javax.swing.JComboBox<>();
        tanggalBayar = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelSPP = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelTotalBayar = new javax.swing.JLabel();
        comboboxNIS = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("NIS               :");

        jLabel2.setText("Nama            :");

        jLabel3.setText("Kelas             :");

        jLabel4.setText("Pembayaran untuk bulan :");

        jLabel5.setText("Uang Bayar   :");

        jLabel6.setText("Uang Kembali :");

        jLabel7.setText("Tanggal         :");

        jLabel8.setText("Semester       :");

        cbSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSemesterActionPerformed(evt);
            }
        });

        jButtonexit.setText("Exit");
        jButtonexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonexitActionPerformed(evt);
            }
        });

        jButtonkembalikemenu.setText("Kembali ke Menu");
        jButtonkembalikemenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonkembalikemenuActionPerformed(evt);
            }
        });

        jButtonbayar.setText("Bayar");
        jButtonbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbayarActionPerformed(evt);
            }
        });

        jLabel9.setText("Uang SPP/ Bulan :");

        jLabelSPP.setText("150000");

        jLabel11.setText("Total Bayar     :");

        comboboxNIS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonbayar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonkembalikemenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonexit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboboxNIS, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(19, 19, 19)
                                        .addComponent(cbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(tanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel11))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtuangbayar, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                            .addComponent(jLabelTotalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(CbBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelSPP, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lblUangkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 169, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(comboboxNIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(tanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(CbBulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtuangbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelSPP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jLabelTotalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lblUangkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonexit)
                    .addComponent(jButtonkembalikemenu)
                    .addComponent(jButtonbayar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonkembalikemenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonkembalikemenuActionPerformed
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonkembalikemenuActionPerformed

    private void jButtonexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonexitActionPerformed
        new Admin().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonexitActionPerformed

    private void jButtonbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbayarActionPerformed
        int bayar = Integer.parseInt(txtuangbayar.getText());
        if (bayar < 0) {
            JOptionPane.showMessageDialog(null, "Maaf Uang yang Anda berikan Kurang");
        } else {
//            int totalBulan = Integer.parseInt(CbBulanBayar.getSelectedItem().toString());
            int totalBayar = spp;
            int uangBayar = Integer.parseInt(txtuangbayar.getText());
            int uangKembali = uangBayar - totalBayar;

            if (bayar < totalBayar) {
                JOptionPane.showMessageDialog(null, "Maaf Uang yang Anda masukkan tidak cukup");
            } else {
                jLabelTotalBayar.setText(Integer.toString(totalBayar));
                lblUangkembali.setText(Integer.toString(uangKembali));
                String query = "INSERT INTO pembayaran(NIS,kelas,semester,tanggal,bulan,totalbayar,uangbayar) VALUES (?,?,?,?,?,?,?)";
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
                Date date = new Date();

                try {
                    data.clear();
                    data.add(comboboxNIS.getSelectedItem().toString());
                    data.add(txtKelas.getText());
                    data.add(cbSemester.getSelectedItem().toString());
                    data.add(tanggalBayar.getText());
                    data.add(CbBulanBayar.getSelectedItem().toString());
                    data.add(Integer.toString(totalBayar));
                    data.add(Integer.toString(uangBayar));
//                data.add("0");
//                data.add(date.toString());

                    PreparedStatement stmt = conn.prepareStatement(query);
                    for (int i = 0; i < data.size(); i++) {
                        stmt.setString((i + 1), data.get(i));
                    }

                    stmt.executeUpdate();

                    //            JOptionPane.showMessageDialog(null, "Berhasil Menabung");
                    TotalBayar();

                } catch (Exception e) {
                    System.out.println("Error : " + e);

                }

            }

        }
    }//GEN-LAST:event_jButtonbayarActionPerformed

    private void cbSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSemesterActionPerformed

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
            java.util.logging.Logger.getLogger(BayarSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BayarSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BayarSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BayarSPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BayarSPP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbBulanBayar;
    private javax.swing.JComboBox<String> cbSemester;
    private javax.swing.JComboBox<String> comboboxNIS;
    private javax.swing.JButton jButtonbayar;
    private javax.swing.JButton jButtonexit;
    private javax.swing.JButton jButtonkembalikemenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNama;
    private javax.swing.JLabel jLabelSPP;
    private javax.swing.JLabel jLabelTotalBayar;
    private javax.swing.JLabel lblUangkembali;
    private javax.swing.JLabel tanggalBayar;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtuangbayar;
    // End of variables declaration//GEN-END:variables
}
