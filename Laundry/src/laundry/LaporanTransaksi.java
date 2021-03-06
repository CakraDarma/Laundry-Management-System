package laundry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LaporanTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form LaporanTransaksi
     */
    DefaultTableModel model;
    Connection conn = Koneksi.getConnection();
    public LaporanTransaksi() {
        initComponents();
        this.setTitle("Laporan Transaksi");
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel();
        TableData.setModel(model);
        model.addColumn("ID Cucian");
        model.addColumn("Nama Pelanggan");
        model.addColumn("No HP");
        model.addColumn("Alamat");
        model.addColumn("Berat (kg)");
        model.addColumn("Tanggal Masuk");
        model.addColumn("Harga");
        model.addColumn("Status Bayar");
        model.addColumn("Status Transaksi");
        model.addColumn("Tanggal Keluar");
        jDateChooser.setDateFormatString("dd/MM/yyyy");
        tampil();
    }

    private void sortDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        jDateChooser.setDateFormatString("yyyy/MM/dd");
        String date = sdf.format(jDateChooser.getDate());

        
        String sql = "SELECT * FROM tb_transaksi JOIN tb_pelanggan WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND tanggal_keluar = ? AND status_transaksi = 'Selesai'";
        
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,date);
          
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
                o[9] = result.getString("tanggal_keluar");
                model.addRow(o);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    private void search(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        String sql = "SELECT * FROM tb_transaksi JOIN tb_pelanggan WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND nama LIKE'"+txtSearch.getText()+"%' AND status_transaksi = 'Selesai'";
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
                o[9] = result.getString("tanggal_keluar");
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
                String query = "SELECT * FROM tb_transaksi JOIN tb_pelanggan WHERE tb_transaksi.id_pelanggan = tb_pelanggan.id_pelanggan AND status_transaksi = 'Selesai'";
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
                        o[9] = result.getString("tanggal_keluar");
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableData = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        btnSbd = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        label_ket_kembali = new javax.swing.JLabel();
        label_back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 100));

        jLabel1.setFont(new java.awt.Font("Shree Devanagari 714", 1, 36)); // NOI18N
        jLabel1.setText("Laporan Transaksi");

        TableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TableData);

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

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel2.setText("Search");

        jDateChooser.setMinSelectableDate(new java.util.Date(-62135794688000L));

        btnSbd.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        btnSbd.setText("SBD");
        btnSbd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSbdActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_report.png"))); // NOI18N

        label_ket_kembali.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali.setText("Kembali");

        label_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_back.png"))); // NOI18N
        label_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_ket_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(label_back)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSbd)))))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSbd))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label_back)
                        .addGap(6, 6, 6)
                        .addComponent(label_ket_kembali))
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        if (txtSearch.getText().isEmpty()) {
            tampil();
        }else{
            search();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnSbdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSbdActionPerformed
        // TODO add your handling code here:
        if (jDateChooser.getDate().equals("")) {
            tampil();
        }else{
            sortDate();
        }
    }//GEN-LAST:event_btnSbdActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageFormat header = new MessageFormat("Data Transaksi");
                MessageFormat footer = new MessageFormat("Page{0,number,integer}");
                try {
                    TableData.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void label_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_backMouseClicked
        new laundry.HomePage2().show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_label_backMouseClicked

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
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableData;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSbd;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_back;
    private javax.swing.JLabel label_ket_kembali;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
