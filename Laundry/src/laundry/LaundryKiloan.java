/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class LaundryKiloan extends javax.swing.JFrame {

    /**
     * Creates new form SetorLaundry
     */
    Connection conn = Koneksi.getConnection();
    private DefaultTableModel model;
    String selectedItemStrNama,selectedItemStr,KDID,jcnya,selectedItemStrPengerjaan, harga, harga_jc;
    String selecDiskonNama,selectDiskonStr, id_diskon, nama_diskon, harga_diskon;
    public LaundryKiloan() {
        initComponents();
        this.setTitle("Input Pesanan");
        model = new DefaultTableModel();
        jTable_CucianMasuk.setModel(model);
        model.addColumn("ID Cucian");
        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Alamat");
        model.addColumn("Berat (kg)");
        model.addColumn("Tanggal Masuk");
        model.addColumn("Total");
        model.addColumn("Status Bayar");
        model.addColumn("Status Transaksi");
        model.addColumn("Jenis Kelamin");
        label_namaAdmin.setText(String.valueOf(adm.nameAdm));
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
        
        String sql = "SELECT * FROM tb_transaksi JOIN tb_pelanggan WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND nama LIKE'"+txtSearch.getText()+"%' AND status_transaksi='On Process'";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while(result.next()){
            Object[] o = new Object[10];
                o[0] = result.getString("id_transaksi");
                o[1] = result.getString("nama");
                o[2] = result.getString("no_telepon");
                o[3] = result.getString("alamat");
                o[4] = result.getString("berat");
                o[5] = result.getString("tanggal_masuk");
                o[6] = result.getInt("total_harga");
                o[7] = result.getString("status_bayar");
                o[8] = result.getString("status_transaksi");    
                o[9] = result.getString("jenis_kelamin");    
                
                model.addRow(o);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    
    private void tampil(){
            try{
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                String query = "SELECT * FROM tb_transaksi JOIN tb_pelanggan WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND status_transaksi='On Process'";
                Statement statement = (Statement) conn.createStatement();
                ResultSet result = statement.executeQuery(query);
                if (!result.next()) {
                    JOptionPane.showMessageDialog(this,"Data Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
                }else{
                    while(result.next()){
                        Object[] o = new Object[10];
                        o[0] = result.getString("id_transaksi");
                        o[1] = result.getString("nama");
                        o[2] = result.getString("no_telepon");
                        o[3] = result.getString("alamat");
                        o[4] = result.getString("berat");
                        o[5] = result.getString("tanggal_masuk");
                        o[6] = result.getInt("total_harga");
                        o[7] = result.getString("status_bayar");
                        o[8] = result.getString("status_transaksi");
                        o[9] = result.getString("jenis_kelamin"); 
                        
                        model.addRow(o);
                    }
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this, ex);
            }
    }
    
    private void reset(){
        txt_Nama.setText("");
        txt_Alamat.setText("");
        txt_NoHP.setText("");
        jk.clearSelection();
        txt_Berat.setText("");
        txt_Harga.setText("");
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jk = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        label_namaAdmin = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txt_Nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_Alamat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_NoHP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton_Laki = new javax.swing.JRadioButton();
        jRadioButton_Perempuan = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txt_Berat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_Harga = new javax.swing.JTextField();
        date_masuk = new com.toedter.calendar.JDateChooser();
        jenis = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Diskon = new javax.swing.JLabel();
        diskon = new javax.swing.JComboBox<>();
        txt_jasa = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_jumlah_diskon = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        ComboStatusBayar = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        ComboStatusTransaksi = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        label_judul = new javax.swing.JLabel();
        label_back = new javax.swing.JLabel();
        label_add = new javax.swing.JLabel();
        label_del = new javax.swing.JLabel();
        label_update = new javax.swing.JLabel();
        jButton_Hitung = new javax.swing.JButton();
        label_ket_kembali = new javax.swing.JLabel();
        label_ket_kembali1 = new javax.swing.JLabel();
        label_ket_kembali2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_CucianMasuk = new javax.swing.JTable();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));

        label_namaAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(1000, 650));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Data Pelanggan");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Alamat");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Nomer HP");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Jenis Kelamin");

        jk.add(jRadioButton_Laki);
        jRadioButton_Laki.setText("Laki Laki");

        jk.add(jRadioButton_Perempuan);
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
                        .addComponent(jLabel2)
                        .addComponent(txt_Nama)
                        .addComponent(jLabel4)
                        .addComponent(txt_Alamat)
                        .addComponent(jLabel6)
                        .addComponent(txt_NoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jRadioButton_Laki)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jRadioButton_Perempuan))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Laki)
                    .addComponent(jRadioButton_Perempuan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Data Cucian");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Berat (Kg)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Tanggal Masuk");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setText("Total Harga (Rp)");

        txt_Harga.setEditable(false);

        date_masuk.setEnabled(false);

        jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Jenis Jasa" }));
        jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisActionPerformed(evt);
            }
        });

        jLabel15.setText("Jenis Jasa");

        jLabel16.setText("Harga jasa");

        Diskon.setText("Jensi Diskon");

        diskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-PIlih Diskon" }));
        diskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diskonActionPerformed(evt);
            }
        });

        txt_jasa.setEditable(false);
        txt_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jasaActionPerformed(evt);
            }
        });

        jLabel17.setText("Jumlah Diskon");

        txt_jumlah_diskon.setEditable(false);
        txt_jumlah_diskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlah_diskonActionPerformed(evt);
            }
        });

        jLabel18.setText("%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(date_masuk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Berat, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Harga, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_jumlah_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addComponent(jLabel17)
                    .addComponent(diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(63, 63, 63))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(Diskon))
                            .addContainerGap()))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Berat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addComponent(date_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Diskon, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jumlah_diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setText("Controls");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Status Pembayaran");

        ComboStatusBayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Lunas", "Lunas" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Status Transaksi");

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
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(ComboStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboStatusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboStatusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComboStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jLabel14.setText("Search");

        jButton1.setText("Satuan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        label_judul.setFont(new java.awt.Font("Shree Devanagari 714", 1, 36)); // NOI18N
        label_judul.setText("Cucian Masuk");

        label_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_back.png"))); // NOI18N
        label_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_backMouseClicked(evt);
            }
        });

        label_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_tambah.png"))); // NOI18N
        label_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_addMouseClicked(evt);
            }
        });

        label_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_delete.png"))); // NOI18N
        label_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_delMouseClicked(evt);
            }
        });

        label_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_save.png"))); // NOI18N
        label_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_updateMouseClicked(evt);
            }
        });

        jButton_Hitung.setText("Hitung");
        jButton_Hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HitungActionPerformed(evt);
            }
        });

        label_ket_kembali.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali.setText("Tambah");

        label_ket_kembali1.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali1.setText("Hapus");

        label_ket_kembali2.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali2.setText("Update");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_back)
                        .addGap(440, 440, 440)
                        .addComponent(label_judul)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(label_ket_kembali1)
                                                .addGap(39, 39, 39)
                                                .addComponent(label_ket_kembali))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(label_del)
                                                .addGap(58, 58, 58)
                                                .addComponent(label_add)))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_ket_kembali2)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(label_update))))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(21, 21, 21)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton_Hitung)))))
                            .addComponent(jScrollPane1))
                        .addGap(0, 175, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(label_judul))
                            .addComponent(jButton1))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Hitung))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(label_back)
                        .addGap(85, 85, 85)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)))
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_del)
                    .addComponent(label_add)
                    .addComponent(label_update))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ket_kembali1)
                    .addComponent(label_ket_kembali)
                    .addComponent(label_ket_kembali2))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_HitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HitungActionPerformed
        // TODO add your handling code here:
        if (txt_Berat.getText().equals("")||txt_jumlah_diskon.getText().equals("")||txt_jasa.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Isi Semua Data!","Notification",JOptionPane.WARNING_MESSAGE); 
        }else{
        try{
            String str = txt_Berat.getText();
            if (str.length()==0) {
                JOptionPane.showMessageDialog(this,"Isi berat terlebih dahulu!","Notification",JOptionPane.WARNING_MESSAGE);
            }else{
                String kg, cost;
                int berat, tot_harga, int_harga;
                float float_diskon, float_tmp;
                
                kg = txt_Berat.getText();
                berat = Integer.parseInt(kg);
                int_harga= Integer.parseInt(harga_jc);
                float_diskon = 100 - Float.parseFloat(harga_diskon);
                
                
                float_tmp = int_harga*(float_diskon/100);
                tot_harga = berat* (int) float_tmp;
                
                cost = String.valueOf(tot_harga);
                txt_Harga.setText(cost);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Inputan harus angka!","Notification",JOptionPane.WARNING_MESSAGE);
        }
        }
    }//GEN-LAST:event_jButton_HitungActionPerformed

    private void jTable_CucianMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CucianMasukMouseClicked
        // TODO add your handling code here:
        txt_Nama.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),1).toString());
        txt_NoHP.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),2).toString());
        txt_Alamat.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),3).toString());
        String tanggal = (model.getValueAt(jTable_CucianMasuk.getSelectedRow(),5).toString());
        date_masuk.setDateFormatString(tanggal);
        String jk1 = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),9).toString();
        if (jk1.equalsIgnoreCase("laki-laki")) {
            jRadioButton_Laki.setSelected(true);
        }else if (jk1.equalsIgnoreCase("perempuan")) {
            jRadioButton_Perempuan.setSelected(true);
        }
        txt_Berat.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),4).toString());
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date createDate = null;
            createDate = df.parse(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),5).toString());
            date_masuk.setDate(createDate);
        }catch(Exception e){
            System.out.println(e);
        }
        txt_Harga.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),6).toString());
        ComboStatusBayar.setSelectedItem(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),7));
        ComboStatusTransaksi.setSelectedItem(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),8));
    }//GEN-LAST:event_jTable_CucianMasukMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.getText().isEmpty()) {
            tampil();
        }else{
            search();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

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
                        txt_jasa.setText(harga_jc);

                    }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this, ex);
            }
    }//GEN-LAST:event_jenisActionPerformed

    private void txt_jumlah_diskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlah_diskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlah_diskonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new LaundrySatuan().show();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
                        txt_jumlah_diskon.setText(harga_diskon);

                    }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this, ex);
            }
     
    }//GEN-LAST:event_diskonActionPerformed

    private void txt_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jasaActionPerformed

    private void label_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_backMouseClicked
        new HomePage2().show();
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_label_backMouseClicked

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
                try{
                    String id_cuci = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),0).toString();
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, id_cuci);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Data Berhasil Dihapus!","Success",JOptionPane.PLAIN_MESSAGE);
                    tampil();
                    reset();
                    txt_Alamat.setText(id_cuci);
                    
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(this,"Gagal Menghapus Data! "+e,"Failed",JOptionPane.ERROR_MESSAGE);    
                } 
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_label_delMouseClicked

    private void label_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_addMouseClicked
         if (txt_Nama.getText().equals("")||txt_Alamat.getText().equals("")||txt_NoHP.getText().equals("")||txt_Harga.getText().equals("")||txt_Berat.getText().equals("")||jk.getSelection()==null){
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
            String weight = txt_Berat.getText();
            int berat = Integer.parseInt(weight);
            String cost = txt_Harga.getText();
            int id_jc = Integer.parseInt(KDID);
            int harga = Integer.parseInt(cost);
            if (jRadioButton_Laki.isSelected()) {
                jenisKelamin = "Laki-Laki";
            } else if (jRadioButton_Perempuan.isSelected()) {
                jenisKelamin = "Perempuan";
        }
        String statusbayar = ComboStatusBayar.getSelectedItem().toString();
        String statustransaksi = ComboStatusTransaksi.getSelectedItem().toString();
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
                statement2.setInt(2, harga);
                statement2.setString(3, statusbayar);
                statement2.setString(4, statustransaksi);
                statement2.setInt(5, adm.fixId);
                statement2.setInt(6, adm.idAdmin);
                statement2.setInt(7, id_jc);
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(this, "Berhasil Menambahkan!", "Success", JOptionPane.PLAIN_MESSAGE);
                //jasper
                try {
                        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("kilo.jasper"), null, Koneksi.getConnection());
                        JasperViewer.viewReport(jp, false);
                        
                    } catch(JRException e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                }
                
                tampil();
                reset();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal Menambahkan! " + ex, "Failed", JOptionPane.WARNING_MESSAGE);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_label_addMouseClicked

    private void label_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_updateMouseClicked
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date_masuk.setDateFormatString("dd/MM/yyyy");
        
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
            String weight = txt_Berat.getText();
            int berat = Integer.parseInt(weight);
            String cost = txt_Harga.getText();
            int harga = Integer.parseInt(cost);
            if (jRadioButton_Laki.isSelected()) {
                jenisKelamin = "Laki-Laki";
            }else if (jRadioButton_Perempuan.isSelected()) {
                jenisKelamin = "Perempuan";
            }
            String statusbayar = ComboStatusBayar.getSelectedItem().toString();
            String statustransaksi = ComboStatusTransaksi.getSelectedItem().toString();
            Date date = new Date();
            String tanggalNow = dateFormat.format(date);
//            String sql = "UPDATE tb_pelanggan SET nama=?,alamat=?,nomer_telepon=? WHERE id_pelanggan =?";
            String sql2 = "UPDATE tb_transaksi SET berat=?,total_harga=?,status_bayar=?,status_transaksi=?, tanggal_keluar =now() WHERE id_transaksi =?";
            try{
//                PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
//                statement.setString(1, nama);
//                statement.setString(2, alamat);
//                statement.setString(3, noHp);
                
                PreparedStatement statement2 = (PreparedStatement)conn.prepareStatement(sql2);
                statement2.setInt(1, berat);
                statement2.setInt(2, harga);
                statement2.setString(3, statusbayar);
                statement2.setString(4, statustransaksi);
                statement2.setString(5, id_cuci);
                
//              statement.executeUpdate();
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(this,"Berhasil Update!","Success",JOptionPane.PLAIN_MESSAGE);
                reset();
                tampil();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,"Gagal Update! "+e,"Failed",JOptionPane.ERROR_MESSAGE);
            }
        }        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(LaundryKiloan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaundryKiloan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaundryKiloan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaundryKiloan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new LaundryKiloan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboStatusBayar;
    private javax.swing.JComboBox<String> ComboStatusTransaksi;
    private javax.swing.JLabel Diskon;
    private com.toedter.calendar.JDateChooser date_masuk;
    private javax.swing.JComboBox<String> diskon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Hitung;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton_Laki;
    private javax.swing.JRadioButton jRadioButton_Perempuan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable_CucianMasuk;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> jenis;
    private javax.swing.ButtonGroup jk;
    private javax.swing.JLabel label_add;
    private javax.swing.JLabel label_back;
    private javax.swing.JLabel label_del;
    private javax.swing.JLabel label_judul;
    private javax.swing.JLabel label_ket_kembali;
    private javax.swing.JLabel label_ket_kembali1;
    private javax.swing.JLabel label_ket_kembali2;
    private javax.swing.JLabel label_namaAdmin;
    private javax.swing.JLabel label_update;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txt_Alamat;
    private javax.swing.JTextField txt_Berat;
    private javax.swing.JTextField txt_Harga;
    private javax.swing.JTextField txt_Nama;
    private javax.swing.JTextField txt_NoHP;
    private javax.swing.JTextField txt_jasa;
    private javax.swing.JTextField txt_jumlah_diskon;
    // End of variables declaration//GEN-END:variables
}
