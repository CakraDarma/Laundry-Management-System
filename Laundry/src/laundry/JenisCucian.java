package laundry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cakra
 */


public class JenisCucian extends javax.swing.JFrame {
    private Connection conn = laundry.Koneksi.getConnection();
    private DefaultTableModel model;
    
    public JenisCucian() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Data Jasa");
        model = new DefaultTableModel();
        TableData.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama Jasa");
        model.addColumn("Perkilo");
        model.addColumn("Baju");
        model.addColumn("Seprai");
        model.addColumn("Handuk");
        model.addColumn("Jas");
        model.addColumn("Celana Pendek");
        model.addColumn("Tas");
        model.addColumn("Jaket");
        model.addColumn("Celana Panjang");
        model.addColumn("Tanggal");
        
        model.setRowCount(0);
        tampil();
        String id_all = String.valueOf(laundry.adm.idAdmin);
        if (id_all.equals("1")) {
            label_del.setEnabled(true);
            label_add.setEnabled(true);
        }else{
            label_del.setEnabled(false);
            label_add.setEnabled(false);
        }
    }
    
    public void tampil(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        String sql = "SELECT * FROM tb_jenis_cucian";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Object [] obj = new Object[12];
                obj[0] = rs.getInt("id_cucian");
                obj[1] = rs.getString("jenis_cucian");
                obj[2] = rs.getString("harga");
                obj[3] = rs.getString("baju");
                obj[4] = rs.getString("seprai");
                obj[5] = rs.getString("handuk");
                obj[6] = rs.getString("jas");
                obj[7] = rs.getString("celana_pendek");
                obj[8] = rs.getString("tas");
                obj[9] = rs.getString("jaket");
                obj[10] = rs.getString("celana_panjang");
                obj[11] = rs.getString("tanggal");                
                model.addRow(obj);
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    private void reset(){
        txt_harga.setText("");
        txt_cucian.setText("");
        txt_baju.setText("");
        txt_seprai.setText("");
        txt_handuk.setText("");
        txt_jas.setText("");
        txt_celana_pendek.setText("");
        txt_tas.setText("");
        txt_jaket.setText("");
        txt_celana_panjang.setText("");        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableData = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_cucian = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_baju = new javax.swing.JTextField();
        txt_seprai = new javax.swing.JTextField();
        txt_handuk = new javax.swing.JTextField();
        txt_celana_pendek = new javax.swing.JTextField();
        txt_jas = new javax.swing.JTextField();
        txt_jaket = new javax.swing.JTextField();
        txt_tas = new javax.swing.JTextField();
        txt_celana_panjang = new javax.swing.JTextField();
        label_back = new javax.swing.JLabel();
        label_add = new javax.swing.JLabel();
        label_del = new javax.swing.JLabel();
        label_ket_kembali = new javax.swing.JLabel();
        label_hapus = new javax.swing.JLabel();
        label_tambah = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        TableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableData);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Jenis Cucian"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Jenis Cucian :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Harga Perkilo :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel5.setText("Satuan");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Handuk :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Baju :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Seprai :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Jas :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Celana Pendek :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Tas :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Jaket :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Celana Panjang :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_cucian, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addComponent(jLabel7)))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_baju, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_handuk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_seprai, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_celana_pendek, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jaket, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_celana_panjang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(295, 295, 295))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_cucian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(txt_baju, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_celana_pendek, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(txt_seprai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_jaket, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(txt_celana_panjang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel13))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_handuk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_jas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addContainerGap(42, Short.MAX_VALUE))))
        );

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

        label_ket_kembali.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali.setText("Kembali");

        label_hapus.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_hapus.setText("Hapus");

        label_tambah.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_tambah.setText("Tambah");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_ket_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(label_tambah)
                        .addGap(64, 64, 64)
                        .addComponent(label_hapus))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(label_back)
                        .addGap(86, 86, 86)
                        .addComponent(label_add)
                        .addGap(87, 87, 87)
                        .addComponent(label_del)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_back)
                            .addComponent(label_del)))
                    .addComponent(label_add))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ket_kembali)
                    .addComponent(label_tambah)
                    .addComponent(label_hapus))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableDataMouseClicked

    }//GEN-LAST:event_TableDataMouseClicked

    private void label_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_backMouseClicked
        new laundry.HomePage2().show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_label_backMouseClicked

    private void label_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_addMouseClicked
        if (txt_cucian.getText().equals("")||txt_harga.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Lengkapi semua data!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            String query = "INSERT INTO tb_jenis_cucian(jenis_cucian, harga, baju, seprai, handuk, jas, celana_pendek, tas, jaket, celana_panjang, tanggal) VALUES (?,?,?,?,?,?,?,?,?,?,now())";
            String jenis_cucian = txt_cucian.getText();
            String harga = txt_harga.getText();
            String harga_baju = txt_baju.getText();
            String harga_seprai = txt_seprai.getText();
            String harga_handuk = txt_handuk.getText();
            String harga_jas = txt_jas.getText();
            String harga_celana_pendek = txt_celana_pendek.getText();
            String harga_tas = txt_tas.getText();
            String harga_jaket = txt_jaket.getText();
            String harga_celana_panjang = txt_celana_panjang.getText();
            
            try{
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1,jenis_cucian);
                st.setString(2,harga);
                st.setString(3,harga_baju);
                st.setString(4,harga_seprai);
                st.setString(5,harga_handuk);
                st.setString(6,harga_jas);
                st.setString(7,harga_celana_pendek);
                st.setString(8,harga_tas);
                st.setString(9,harga_jaket);
                st.setString(10,harga_celana_panjang);
                
                
                st.executeUpdate();
                JOptionPane.showMessageDialog(this,"Jenis Cucian Ditambahkan!","Success",JOptionPane.PLAIN_MESSAGE);
                tampil();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,"Gagal Menambahkan!","Error",JOptionPane.ERROR_MESSAGE);
            }
            reset();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_label_addMouseClicked

    private void label_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_delMouseClicked
        if (TableData.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this,"Data Belum Dipilih!!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            int dial = JOptionPane.YES_NO_OPTION;
            int dialog = JOptionPane.showConfirmDialog(this,"Apakah Anda Yakin Menghapus?");
            if (dialog == JOptionPane.YES_OPTION) {
                String id = model.getValueAt(TableData.getSelectedRow(),0).toString();
                String sql = "Delete FROM tb_jenis_cucian WHERE id_cucian='"+id+"'";
                try{
                    PreparedStatement st = conn.prepareStatement(sql);
                    st.execute();
                    JOptionPane.showMessageDialog(this,"Terhapus!","Success",JOptionPane.PLAIN_MESSAGE);
                    tampil();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(this,"Gagal Menghapus Data!","Error",JOptionPane.PLAIN_MESSAGE);
                }
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_label_delMouseClicked

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
            java.util.logging.Logger.getLogger(JenisCucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JenisCucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JenisCucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JenisCucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new JenisCucian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableData;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_add;
    private javax.swing.JLabel label_back;
    private javax.swing.JLabel label_del;
    private javax.swing.JLabel label_hapus;
    private javax.swing.JLabel label_ket_kembali;
    private javax.swing.JLabel label_tambah;
    private javax.swing.JTextField txt_baju;
    private javax.swing.JTextField txt_celana_panjang;
    private javax.swing.JTextField txt_celana_pendek;
    private javax.swing.JTextField txt_cucian;
    private javax.swing.JTextField txt_handuk;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_jaket;
    private javax.swing.JTextField txt_jas;
    private javax.swing.JTextField txt_seprai;
    private javax.swing.JTextField txt_tas;
    // End of variables declaration//GEN-END:variables
}
