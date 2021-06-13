/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Cakra
 */
public class LaundrySatuan extends javax.swing.JFrame {
Connection conn = Koneksi.getConnection();
    private DefaultTableModel model;
    String selectedItemStrNama,selectedItemStr,KDID,jcnya,selectedItemStrPengerjaan, harga, harga_jc;
    String selecDiskonNama,selectDiskonStr, id_diskon, nama_diskon, harga_diskon;
    String harga_baju,harga_handuk,harga_seprai, harga_jas, harga_celana_pendek, harga_tas, harga_jaket, harga_celana_panjang;
    int jmlh_baju, jmlh_seprai, jmlh_handuk, jmlh_jas, jmlh_celana_pendek, jmlh_tas, jmlh_jaket, jmlh_celana_panjang;
    /**
     * Creates new form kiloan
     */
    public LaundrySatuan() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Input Pesanan");
        model = new DefaultTableModel();
        jTable_CucianMasuk.setModel(model);
        model.addColumn("ID Cucian");
        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Alamat");
        model.addColumn("Tanggal Masuk");
        model.addColumn("Total");
        model.addColumn("Status Bayar");
        model.addColumn("Status Transaksi");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Baju");
        model.addColumn("Seprai");
        model.addColumn("Handuk");
        model.addColumn("Jas");
        model.addColumn("Celana Pendek");
        model.addColumn("Tas");
        model.addColumn("Jaket");
        model.addColumn("Celana Panjang");

        
        
        this.setLocationRelativeTo(null);
        tanggalNow();
        tampil();
        jenis_cucian_pen();
        jenis_diskon();
    }
    
    
    private void tanggalNow(){
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date_masuk.setDateFormatString("dd/MM/yyyy");
        date_masuk.setDate(date);
    }
    
    private void search(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
//        String sql = "SELECT * FROM tb_transaksi JOIN tb_pelanggan WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND nama LIKE'"+txtSearch.getText()+"%' AND status_transaksi='On Process'";
        String sql = "SELECT `tb_transaksi`.`id_transaksi`, `tb_pelanggan`.`nama`, `tb_pelanggan`.`no_telepon`, `tb_pelanggan`.`alamat`,\n" +
                            "`tb_transaksi`.`tanggal_masuk`, `tb_transaksi`.`total_harga`, tb_transaksi.`status_bayar`, `tb_transaksi`.`status_transaksi`,\n" +
                            "`tb_pelanggan`.`jenis_kelamin`, `tb_det_transaksi`.`baju`, `tb_det_transaksi`.`seprai`, `tb_det_transaksi`.`handuk`,\n" +
                            "`tb_det_transaksi`.`jas`, `tb_det_transaksi`.`celana_pendek`, `tb_det_transaksi`.`tas`, tb_det_transaksi.`jaket`, \n" +
                            "`tb_det_transaksi`.`celana_panjang`\n" +
                            "FROM tb_transaksi\n" +
                            "INNER JOIN tb_pelanggan ON (tb_pelanggan.`id_pelanggan` = `tb_transaksi`.`id_pelanggan`)\n" +
                            "INNER JOIN tb_det_transaksi ON (`tb_det_transaksi`.`id_transaksi` = `tb_transaksi`.`id_transaksi`)\n" +
                            "WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND nama LIKE'"+txtSearch.getText()+"%' AND status_transaksi='On Process';";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while(result.next()){
            Object[] o = new Object[17];
            o[0] = result.getString("id_transaksi");
            o[1] = result.getString("nama");
            o[2] = result.getString("no_telepon");
            o[3] = result.getString("alamat");
            o[4] = result.getString("tanggal_masuk");
            o[5] = result.getInt("total_harga");
            o[6] = result.getString("status_bayar");
            o[7] = result.getString("status_transaksi");
            o[8] = result.getString("jenis_kelamin");
            o[9] = result.getString("baju");
            o[10] = result.getString("seprai");
            o[11] = result.getString("handuk");
            o[12] = result.getString("jas");
            o[13] = result.getString("celana_pendek");
            o[14] = result.getString("tas");
            o[15] = result.getString("jaket");
            o[16] = result.getString("celana_panjang");
                model.addRow(o);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    
    private void reset(){
        txt_Nama.setText("");
        txt_Alamat.setText("");
        txt_NoHP.setText("");
        txt_Harga.setText("");
        txt_baju.setText("");
        txt_seprai.setText("");
        txt_handuk.setText("");
        txt_jas.setText("");
        txt_celana_pendek.setText("");
        txt_tas.setText("");
        txt_jaket.setText("");
        txt_celana_panjang.setText("");
        ComboStatusBayar.setSelectedItem("Belum Lunas");
        ComboStatusTransaksi.setSelectedItem("On Process");
        jRadioButton_Perempuan.setSelected(false);
        jRadioButton_Laki.setSelected(false);
    }
    
    Connection con;
    Statement st;
    private int xx;
    private int xy;
    
    private void jenis_cucian_pen() {
            try{

            String query = "SELECT * FROM tb_jenis_cucian";
            Statement statement = (Statement) conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (!result.next()) {
                JOptionPane.showMessageDialog(this,"Data Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
            }else{
                while(result.next()){
                     jenis.addItem((result.getString(2))); 
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    
    private void jenis_diskon() {
            try{

            String query = "SELECT * FROM tb_diskon WHERE tgl_akhir > now()";
            Statement statement = (Statement) conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (!result.next()) {
                JOptionPane.showMessageDialog(this,"Data Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
            }else{
                while(result.next()){
                     diskon.addItem((result.getString(2))); 
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    
    
    private void tampil(){
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            String query = "SELECT `tb_transaksi`.`id_transaksi`, `tb_pelanggan`.`nama`, `tb_pelanggan`.`no_telepon`, `tb_pelanggan`.`alamat`,\n" +
                            "`tb_transaksi`.`tanggal_masuk`, `tb_transaksi`.`total_harga`, tb_transaksi.`status_bayar`, `tb_transaksi`.`status_transaksi`,\n" +
                            "`tb_pelanggan`.`jenis_kelamin`, `tb_det_transaksi`.`baju`, `tb_det_transaksi`.`seprai`, `tb_det_transaksi`.`handuk`,\n" +
                            "`tb_det_transaksi`.`jas`, `tb_det_transaksi`.`celana_pendek`, `tb_det_transaksi`.`tas`, tb_det_transaksi.`jaket`, \n" +
                            "`tb_det_transaksi`.`celana_panjang`\n" +
                            "FROM tb_transaksi\n" +
                            "INNER JOIN tb_pelanggan ON (tb_pelanggan.`id_pelanggan` = `tb_transaksi`.`id_pelanggan`)\n" +
                            "INNER JOIN tb_det_transaksi ON (`tb_det_transaksi`.`id_transaksi` = `tb_transaksi`.`id_transaksi`)\n" +
                            "WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND status_transaksi='On Process';";
            Statement statement = (Statement) conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (!result.next()) {
                JOptionPane.showMessageDialog(this,"Data Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
            }else{
                while(result.next()){
                    Object[] o = new Object[17];
                    o[0] = result.getString("id_transaksi");
                    o[1] = result.getString("nama");
                    o[2] = result.getString("no_telepon");
                    o[3] = result.getString("alamat");
                    o[4] = result.getString("tanggal_masuk");
                    o[5] = result.getInt("total_harga");
                    o[6] = result.getString("status_bayar");
                    o[7] = result.getString("status_transaksi");
                    o[8] = result.getString("jenis_kelamin");
                    o[9] = result.getString("baju");
                    o[10] = result.getString("seprai");
                    o[11] = result.getString("handuk");
                    o[12] = result.getString("jas");
                    o[13] = result.getString("celana_pendek");
                    o[14] = result.getString("tas");
                    o[15] = result.getString("jaket");
                    o[16] = result.getString("celana_panjang");
                    

                    model.addRow(o);
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        ComboStatusBayar = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        ComboStatusTransaksi = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txt_Nama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Alamat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_NoHP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton_Laki = new javax.swing.JRadioButton();
        jRadioButton_Perempuan = new javax.swing.JRadioButton();
        label_back = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_CucianMasuk = new javax.swing.JTable();
        label_update = new javax.swing.JLabel();
        label_add = new javax.swing.JLabel();
        label_ket_kembali1 = new javax.swing.JLabel();
        label_ket_kembali = new javax.swing.JLabel();
        label_ket_kembali2 = new javax.swing.JLabel();
        label_del = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_harga_normal = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_besar_diskon = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_Harga = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        label_judul = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txt_baju = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_seprai = new javax.swing.JTextField();
        txt_handuk = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_jas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        date_masuk = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jenis = new javax.swing.JComboBox<>();
        Diskon = new javax.swing.JLabel();
        diskon = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_celana_pendek = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_tas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_jaket = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_celana_panjang = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton_Hitung = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txt_diskon = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setText("Controls");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel15.setText("Status Pembayaran");

        ComboStatusBayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Lunas", "Lunas" }));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel16.setText("Status Transaksi");

        ComboStatusTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "On Process", "Selesai" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(ComboStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboStatusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboStatusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComboStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Data Pelanggan");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Nama");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Alamat");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Nomer HP");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Jenis Kelamin");

        jRadioButton_Laki.setText("Laki Laki");

        jRadioButton_Perempuan.setText("Perempuan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(txt_Nama)
                        .addComponent(jLabel5)
                        .addComponent(txt_Alamat)
                        .addComponent(jLabel6)
                        .addComponent(txt_NoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jRadioButton_Laki)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jRadioButton_Perempuan))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Laki)
                    .addComponent(jRadioButton_Perempuan))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        label_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_back.png"))); // NOI18N
        label_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_backMouseClicked(evt);
            }
        });

        jTable_CucianMasuk.setForeground(new java.awt.Color(51, 51, 51));
        jTable_CucianMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_CucianMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CucianMasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_CucianMasuk);

        label_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_save.png"))); // NOI18N
        label_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_updateMouseClicked(evt);
            }
        });

        label_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_tambah.png"))); // NOI18N
        label_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_addMouseClicked(evt);
            }
        });

        label_ket_kembali1.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali1.setText("Hapus");

        label_ket_kembali.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali.setText("Tambah");

        label_ket_kembali2.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali2.setText("Update");

        label_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_delete.png"))); // NOI18N
        label_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_delMouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel25.setText("Harga Normal");

        txt_harga_normal.setEditable(false);
        txt_harga_normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_harga_normalActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel26.setText("Besar Diskon");

        txt_besar_diskon.setEditable(false);
        txt_besar_diskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_besar_diskonActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel24.setText("Total Harga (Rp)");

        txt_Harga.setEditable(false);

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel17.setText("Search");

        label_judul.setFont(new java.awt.Font("Shree Devanagari 714", 1, 36)); // NOI18N
        label_judul.setText("Laundry Satuan");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel27.setText("Baju");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Seprai");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Handuk");

        txt_seprai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sepraiActionPerformed(evt);
            }
        });

        txt_handuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_handukActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setText("Jas");

        txt_jas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Tanggal Masuk");

        date_masuk.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel18.setText("Jenis Jasa");

        jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Jenis Jasa" }));
        jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisActionPerformed(evt);
            }
        });

        Diskon.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Diskon.setText("Jenis Diskon");

        diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-PIlih Diskon" }));
        diskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diskonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_baju, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_seprai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_jas, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_handuk, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1)
                    .addComponent(date_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Diskon))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txt_baju, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_seprai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_handuk, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_jas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(Diskon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Celana Pendek");

        txt_celana_pendek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_celana_pendekActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Tas");

        txt_tas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tasActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Jaket");

        txt_jaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jaketActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setText("Celana Panjang");

        txt_celana_panjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_celana_panjangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txt_celana_panjang, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_jaket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_celana_pendek, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_celana_pendek, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_tas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_jaket, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_celana_panjang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jButton_Hitung.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jButton_Hitung.setText("Hitung");
        jButton_Hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HitungActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel19.setText("Diskon");

        txt_diskon.setEditable(false);
        txt_diskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diskonActionPerformed(evt);
            }
        });

        jLabel21.setText("%");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_ket_kembali1)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(label_del)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(label_ket_kembali))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(label_add)))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_ket_kembali2)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(label_update)))
                                .addGap(53, 53, 53)
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(txt_harga_normal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(txt_besar_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 168, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_back)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton_Hitung)
                                .addGap(78, 78, 78)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21))))
                            .addComponent(label_judul)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(label_judul, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label_update)
                            .addComponent(label_del)
                            .addComponent(label_add))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_ket_kembali1)
                            .addComponent(label_ket_kembali)
                            .addComponent(label_ket_kembali2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_harga_normal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(txt_besar_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26)
                        .addComponent(jLabel24)
                        .addComponent(txt_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_sepraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sepraiActionPerformed
        
    }//GEN-LAST:event_txt_sepraiActionPerformed

    private void txt_handukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_handukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_handukActionPerformed

    private void txt_celana_pendekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_celana_pendekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_celana_pendekActionPerformed

    private void txt_tasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tasActionPerformed

    private void txt_jaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jaketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jaketActionPerformed

    private void txt_jasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jasActionPerformed

    private void txt_celana_panjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_celana_panjangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_celana_panjangActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.getText().isEmpty()) {
            tampil();
        }else{
            search();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jTable_CucianMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CucianMasukMouseClicked
        // TODO add your handling code here:
        txt_Nama.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),1).toString());
        txt_NoHP.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),2).toString());
        txt_Alamat.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),3).toString());
        String jk2 = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),8).toString();
        if (jk2.equalsIgnoreCase("laki-laki")) {
            jRadioButton_Laki.setSelected(true);
        }else if (jk2.equalsIgnoreCase("perempuan")) {
            jRadioButton_Perempuan.setSelected(true);
        }
        String tanggal = (model.getValueAt(jTable_CucianMasuk.getSelectedRow(),4).toString());
        date_masuk.setDateFormatString(tanggal);
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date createDate = null;
            createDate = df.parse(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),5).toString());
            date_masuk.setDate(createDate);
        }catch(Exception e){
            System.out.println(e);
        }
        txt_Harga.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),5).toString());
        ComboStatusBayar.setSelectedItem(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),6));
        ComboStatusTransaksi.setSelectedItem(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),7));
        //barang
        txt_baju.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),9).toString());
        txt_seprai.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),10).toString());
        txt_handuk.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),11).toString());
        txt_jas.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),12).toString());
        txt_celana_pendek.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),13).toString());
        txt_tas.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),14).toString());
        txt_jaket.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),15).toString());
        txt_celana_panjang.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),16).toString());
    }//GEN-LAST:event_jTable_CucianMasukMouseClicked

    private void jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisActionPerformed
        Object selectedItem = jenis.getSelectedItem();
        selectedItemStr = selectedItem.toString();
        try{
            String query = "SELECT * FROM tb_jenis_cucian WHERE jenis_cucian='"+selectedItemStr+"'";
            Statement statement = (Statement) conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                KDID = result.getString("id_cucian");
                jcnya = result.getString("jenis_cucian");
                harga_jc = result.getString("harga");
                harga_handuk = result.getString("handuk");
                harga_baju = result.getString("baju");
                harga_seprai = result.getString("seprai");
                harga_jas = result.getString("jas");
                harga_celana_pendek = result.getString("celana_pendek");
                harga_tas = result.getString("tas");
                harga_jaket = result.getString("jaket");
                harga_celana_panjang = result.getString("celana_panjang");
                
                

            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jenisActionPerformed

    private void diskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diskonActionPerformed
        Object selectedItem = diskon.getSelectedItem();
        selectDiskonStr = selectedItem.toString();
        try{
            String query = "SELECT * FROM tb_diskon WHERE diskon='"+selectDiskonStr+"'";
            Statement statement = (Statement) conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                id_diskon = result.getString("id_diskon");
                nama_diskon = result.getString("diskon");
                harga_diskon = result.getString("jumlah");
                txt_diskon.setText(harga_diskon);

            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }

    }//GEN-LAST:event_diskonActionPerformed

    private void txt_diskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_diskonActionPerformed

    private void txt_besar_diskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_besar_diskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_besar_diskonActionPerformed

    private void jButton_HitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HitungActionPerformed
        // TODO add your handling code here:
        if (txt_diskon.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Isi Semua Data!","Notification",JOptionPane.WARNING_MESSAGE); 
        }else{
        try{
                String kg, cost;
                int berat, tot_harga, int_harga, besar_diskon;
                float float_diskon, float_tmp;
                
                float_diskon = 100 - Float.parseFloat(harga_diskon);
                
                // menghitung jumlah barang
                if (txt_baju.getText().equals("")){
                    jmlh_baju = 0;
                } else{
                    jmlh_baju = Integer.parseInt(txt_baju.getText());
                }
                
                if (txt_seprai.getText().equals("")){
                    jmlh_seprai = 0;
                } else{
                    jmlh_seprai = Integer.parseInt(txt_seprai.getText());
                }
                
                if (txt_handuk.getText().equals("")){
                    jmlh_handuk = 0;
                } else{
                    jmlh_handuk = Integer.parseInt(txt_handuk.getText());
                }   
                                
                if (txt_jas.getText().equals("")){
                    jmlh_jas = 0;
                } else{
                    jmlh_jas = Integer.parseInt(txt_jas.getText());
                } 
                                
                if (txt_celana_pendek.getText().equals("")){
                    jmlh_celana_pendek = 0;
                } else{
                    jmlh_celana_pendek = Integer.parseInt(txt_celana_pendek.getText());
                } 
                                
                if (txt_tas.getText().equals("")){
                    jmlh_tas = 0;
                } else{
                    jmlh_tas = Integer.parseInt(txt_tas.getText());
                } 
                                
                if (txt_jaket.getText().equals("")){
                    jmlh_jaket = 0;
                } else{
                    jmlh_jaket = Integer.parseInt(txt_jaket.getText());
                } 
                                
                if (txt_celana_panjang.getText().equals("")){
                    jmlh_celana_panjang = 0;
                } else{
                    jmlh_celana_panjang = Integer.parseInt(txt_celana_panjang.getText());
                }
                
                //menghitung harga dari jmlh x harga satuan
                int int_harga_baju= Integer.parseInt(harga_baju) * jmlh_baju;
                int int_harga_handuk = Integer.parseInt(harga_handuk) * jmlh_handuk;
                int int_harga_seprai= Integer.parseInt(harga_seprai) * jmlh_seprai;
                int int_harga_jas= Integer.parseInt(harga_jas) * jmlh_jas;
                int int_harga_celana_pendek= Integer.parseInt(harga_celana_pendek) * jmlh_celana_pendek;
                int int_harga_tas= Integer.parseInt(harga_tas) * jmlh_tas;
                int int_harga_jaket= Integer.parseInt(harga_jaket) * jmlh_jaket;
                int int_harga_celana_panjang= Integer.parseInt(harga_celana_panjang) * jmlh_celana_panjang;


                //menghitung total harga seluruh barang
                int_harga = int_harga_handuk + int_harga_seprai + int_harga_baju + int_harga_jas + int_harga_celana_pendek + int_harga_tas + int_harga_jaket + int_harga_celana_panjang;
                
                //menghitung dengan diskon
                float_tmp = int_harga*(float_diskon/100);
                tot_harga = 1* (int) float_tmp;
                besar_diskon = int_harga - tot_harga;

                cost = String.valueOf(tot_harga);
                txt_Harga.setText(cost);
                txt_besar_diskon.setText(String.valueOf(besar_diskon));
                txt_harga_normal.setText(String.valueOf(int_harga));
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Inputan Anda Salah!","Notification",JOptionPane.WARNING_MESSAGE);
        }
        }
    }//GEN-LAST:event_jButton_HitungActionPerformed

    private void txt_harga_normalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_harga_normalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_harga_normalActionPerformed

    private void label_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_backMouseClicked
        new HomePage2().show();
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_label_backMouseClicked

    private void label_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_addMouseClicked
        if (txt_Nama.getText().equals("")||txt_Alamat.getText().equals("")||txt_NoHP.getText().equals("")||txt_Harga.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Isi Semua Data!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if (ComboStatusBayar.getSelectedItem()=="Belum Lunas" && ComboStatusTransaksi.getSelectedItem()=="Selesai") {
            JOptionPane.showMessageDialog(this,"Pembayaran Belum Lunas!","Notification",JOptionPane.WARNING_MESSAGE);
        }else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date_masuk.setDateFormatString("dd/MM/yyyy");
            String strdate = dateFormat.format(date_masuk.getDate());
            String nama = txt_Nama.getText();
            String alamat = txt_Alamat.getText();
            String noHp = txt_NoHP.getText();
            String jenisKelamin = null;
            int berat = 0;

            
            String cost = txt_Harga.getText();
            int id_jc = Integer.parseInt(KDID);
            int txt_harga = Integer.parseInt(cost);
            int newId_trans = 9 ;
            
            if (jRadioButton_Laki.isSelected()) {
                jenisKelamin = "Laki-Laki";
            } else if (jRadioButton_Perempuan.isSelected()) {
                jenisKelamin = "Perempuan";
            }
            String statusbayar = ComboStatusBayar.getSelectedItem().toString();
            String statustransaksi = ComboStatusTransaksi.getSelectedItem().toString();
            
//            int jmlh_baju = Integer.parseInt(txt_baju.getText());
//            int jmlh_seprai = Integer.parseInt(txt_seprai.getText());
//            int jmlh_handuk = Integer.parseInt(txt_handuk.getText());
//            int jmlh_jas = Integer.parseInt(txt_jas.getText());
//            int jmlh_celana_pendek = Integer.parseInt(txt_celana_pendek.getText());
//            int jmlh_tas = Integer.parseInt(txt_tas.getText());
//            int jmlh_jaket = Integer.parseInt(txt_jaket.getText());
//            int jmlh_celana_panjang = Integer.parseInt(txt_celana_panjang.getText());
            
            try {
                String queryId = "SELECT id_pelanggan FROM tb_pelanggan ORDER BY id_pelanggan DESC LIMIT 1";
                try {
                    Statement statement = conn.createStatement();
                    ResultSet resultId = statement.executeQuery(queryId);
                    while (resultId.next()) {
                        int newId = resultId.getInt("id_pelanggan");
                        adm.fixId = newId + 1;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal Mendapatkan Id", "Failed", JOptionPane.ERROR_MESSAGE);
                }
                
                String queryId2 = "SELECT MAX(id_transaksi) AS id_trans FROM tb_transaksi;";
                try {
                    Statement statement2 = conn.createStatement();
                    ResultSet resultId2 = statement2.executeQuery(queryId2);
                    while (resultId2.next()) {
                        newId_trans = resultId2.getInt("id_trans") + 1;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal Mendapatkan Id", "Failed", JOptionPane.ERROR_MESSAGE);
                }
                
                String query = "INSERT INTO tb_pelanggan(nama, alamat, no_telepon, jenis_kelamin) VALUES (?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) conn.prepareStatement(query);
                statement.setString(1, nama);
                statement.setString(2, alamat);
                statement.setString(3, noHp);
                statement.setString(4, jenisKelamin);
                statement.executeUpdate();

                String query2 = "INSERT INTO tb_transaksi(berat, tanggal_masuk, total_harga, status_bayar, status_transaksi, id_pelanggan, id_pengguna, id_jenis_cucian) VALUES(?,now(),?,?,?,?,?,?)";
                PreparedStatement statement2 = (PreparedStatement) conn.prepareStatement(query2);
                statement2.setInt(1, berat);
                statement2.setInt(2, txt_harga);
                statement2.setString(3, statusbayar);
                statement2.setString(4, statustransaksi);
                statement2.setInt(5, adm.fixId);
                statement2.setInt(6, adm.idAdmin);
                statement2.setInt(7, id_jc);
                statement2.executeUpdate();
                
                String query3 = "INSERT INTO tb_det_transaksi(id_transaksi, baju, seprai, handuk, jas, celana_pendek, tas, jaket, celana_panjang) VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement3 = (PreparedStatement) conn.prepareStatement(query3);
                statement3.setInt(1, newId_trans);
                statement3.setInt(2, jmlh_baju);
                statement3.setInt(3, jmlh_seprai);
                statement3.setInt(4, jmlh_handuk);
                statement3.setInt(5, jmlh_jas);
                statement3.setInt(6, jmlh_celana_pendek);
                statement3.setInt(7, jmlh_tas);
                statement3.setInt(8, jmlh_jaket);
                statement3.setInt(9, jmlh_celana_panjang);
                
                statement3.executeUpdate();
                
                
                JOptionPane.showMessageDialog(this, "Berhasil Menambahkan!", "Success", JOptionPane.PLAIN_MESSAGE);
                try {
                        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("satuan.jasper"), null, Koneksi.getConnection());
                        JasperViewer.viewReport(jp, false);
                    } catch(JRException e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                }
                
                tampil();
                reset();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal Menambahkan! " + ex, "Failed", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_label_addMouseClicked

    private void label_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_delMouseClicked
        if (jTable_CucianMasuk.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,"Tabel Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if(jTable_CucianMasuk.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this,"Pilih Data yang Akan Dihapus!","Notification",JOptionPane.WARNING_MESSAGE);
        }else{
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this,"Yakin Ingin Menghapus?");
            if (dialogResult == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM tb_transaksi WHERE id_transaksi=?";
                String sql2 = "DELETE FROM tb_det_transaksi WHERE id_transaksi=?";
                try{
                    String id_cuci = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),0).toString();
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, id_cuci);
                    statement.executeUpdate();
                    
                    String id_transaksi = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),0).toString();
                    PreparedStatement statement2 = conn.prepareStatement(sql2);
                    statement2.setString(1, id_cuci);
                    statement2.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Data Berhasil Dihapus!","Success",JOptionPane.PLAIN_MESSAGE);
                    tampil();
                    reset();
                    

                }catch(SQLException e){
                    JOptionPane.showMessageDialog(this,"Gagal Menghapus Data! "+e,"Failed",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_label_delMouseClicked

    private void label_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_updateMouseClicked
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date_masuk.setDateFormatString("dd/MM/yyyy");
            int jmlh_baju1 = Integer.parseInt(txt_baju.getText());
            int jmlh_seprai1 = Integer.parseInt(txt_seprai.getText());
            int jmlh_handuk1 = Integer.parseInt(txt_handuk.getText());
            int jmlh_jas1 = Integer.parseInt(txt_jas.getText());
            int jmlh_celana_pendek1 = Integer.parseInt(txt_celana_pendek.getText());
            int jmlh_tas1 = Integer.parseInt(txt_tas.getText());
            int jmlh_jaket1 = Integer.parseInt(txt_jaket.getText());
            int jmlh_celana_panjang1 = Integer.parseInt(txt_celana_panjang.getText());

        if (jTable_CucianMasuk.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,"Tabel Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if(jTable_CucianMasuk.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this,"Pilih Data yang Akan Diubah!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if(ComboStatusBayar.getSelectedItem()=="Belum Lunas" && ComboStatusTransaksi.getSelectedItem()=="Selesai"){
            JOptionPane.showMessageDialog(this,"Tranaksi Belum Lunas","Notification",JOptionPane.WARNING_MESSAGE);
        }else {
            String id_cuci = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),0).toString();
            String nama = txt_Nama.getText();
            String strdate = dateFormat.format(date_masuk.getDate());
            String alamat = txt_Alamat.getText();
            String noHp = txt_NoHP.getText();
            String jenisKelamin = null;
            String weight = "0";
            int berat = Integer.parseInt(weight);
            String cost = txt_Harga.getText();
            int txt_harga = Integer.parseInt(cost);
            if (jRadioButton_Laki.isSelected()) {
                jenisKelamin = "Laki-Laki";
            }else if (jRadioButton_Perempuan.isSelected()) {
                jenisKelamin = "Perempuan";
            }
            String statusbayar = ComboStatusBayar.getSelectedItem().toString();
            String statustransaksi = ComboStatusTransaksi.getSelectedItem().toString();
            Date date = new Date();
            String tanggalNow = dateFormat.format(date);
            String sql2 = "UPDATE tb_transaksi SET berat=?,total_harga=?,status_bayar=?,status_transaksi=?, tanggal_keluar =now() WHERE id_transaksi =?";
            String sql3 = "UPDATE tb_det_transaksi SET baju=?, seprai=?, handuk=?, jas=?, celana_pendek=?, tas=?, jaket=?, celana_panjang=? WHERE id_transaksi = ?";
            try{

                String id_transaksi_cucian = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),0).toString();
                
                PreparedStatement statement2 = (PreparedStatement)conn.prepareStatement(sql2);
                statement2.setInt(1, berat);
                statement2.setInt(2, txt_harga);
                statement2.setString(3, statusbayar);
                statement2.setString(4, statustransaksi);
                statement2.setString(5, id_cuci);
                statement2.executeUpdate();
                
                PreparedStatement statement3 = (PreparedStatement)conn.prepareStatement(sql3);
                statement3.setInt(1, jmlh_baju1);
                statement3.setInt(2, jmlh_seprai1);
                statement3.setInt(3, jmlh_handuk1);
                statement3.setInt(4, jmlh_jas1);
                statement3.setInt(5, jmlh_celana_pendek1);
                statement3.setInt(6, jmlh_tas1);
                statement3.setInt(7, jmlh_jaket1);
                statement3.setInt(8, jmlh_celana_panjang1);
                statement3.setString(9, id_transaksi_cucian);
               
                statement3.executeUpdate();
                
                JOptionPane.showMessageDialog(this,"Berhasil Update!","Success",JOptionPane.PLAIN_MESSAGE);
                reset();
                tampil();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,"Gagal Update! "+e,"Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_label_updateMouseClicked

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
            java.util.logging.Logger.getLogger(LaundrySatuan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaundrySatuan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaundrySatuan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaundrySatuan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaundrySatuan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboStatusBayar;
    private javax.swing.JComboBox<String> ComboStatusTransaksi;
    private javax.swing.JLabel Diskon;
    private com.toedter.calendar.JDateChooser date_masuk;
    private javax.swing.JComboBox<String> diskon;
    private javax.swing.JButton jButton_Hitung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton_Laki;
    private javax.swing.JRadioButton jRadioButton_Perempuan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable_CucianMasuk;
    private javax.swing.JComboBox<String> jenis;
    private javax.swing.JLabel label_add;
    private javax.swing.JLabel label_back;
    private javax.swing.JLabel label_del;
    private javax.swing.JLabel label_judul;
    private javax.swing.JLabel label_ket_kembali;
    private javax.swing.JLabel label_ket_kembali1;
    private javax.swing.JLabel label_ket_kembali2;
    private javax.swing.JLabel label_update;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txt_Alamat;
    private javax.swing.JTextField txt_Harga;
    private javax.swing.JTextField txt_Nama;
    private javax.swing.JTextField txt_NoHP;
    private javax.swing.JTextField txt_baju;
    private javax.swing.JTextField txt_besar_diskon;
    private javax.swing.JTextField txt_celana_panjang;
    private javax.swing.JTextField txt_celana_pendek;
    private javax.swing.JTextField txt_diskon;
    private javax.swing.JTextField txt_handuk;
    private javax.swing.JTextField txt_harga_normal;
    private javax.swing.JTextField txt_jaket;
    private javax.swing.JTextField txt_jas;
    private javax.swing.JTextField txt_seprai;
    private javax.swing.JTextField txt_tas;
    // End of variables declaration//GEN-END:variables
}
