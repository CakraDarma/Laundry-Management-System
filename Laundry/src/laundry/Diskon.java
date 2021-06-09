package laundry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cakra
 */
public class Diskon extends javax.swing.JFrame {
    private Connection conn = laundry.Koneksi.getConnection();
    private DefaultTableModel model;

    public Diskon() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Data Jasa");
        model = new DefaultTableModel();
        TableData.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Besar Dikosn");
        model.addColumn("Mulai");
        model.addColumn("Berakhir");
        
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
        mulai.setDateFormatString("dd/MM/yyyy");
        akhir.setDateFormatString("dd/MM/yyyy");
    }
    
    public void tampil(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        String sql = "SELECT * FROM tb_diskon";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Object [] obj = new Object[6];
                obj[0] = rs.getInt("id_diskon");
                obj[1] = rs.getString("diskon");
                obj[2] = rs.getString("jumlah");  
                obj[3] = rs.getString("tgl_mulai");  
                obj[4] = rs.getString("tgl_akhir");  
                model.addRow(obj);
            }
            
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
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
        txt_nama = new javax.swing.JTextField();
        txt_besar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        mulai = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        akhir = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        label_add = new javax.swing.JLabel();
        label_del = new javax.swing.JLabel();
        label_back = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

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
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 600));

        TableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Diskon", "Jumlah", "Mulai", "Berakhir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableData);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Jenis Cucian"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nama Diskon");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Besar Diskon");

        jLabel1.setText("%");

        jLabel2.setText("Tanggal Mulai");

        jLabel5.setText("Tanggal Berakhir");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_besar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(mulai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(akhir, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(mulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_besar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5))
                    .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        label_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        label_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_addMouseClicked(evt);
            }
        });

        label_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash.png"))); // NOI18N
        label_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_delMouseClicked(evt);
            }
        });

        label_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/arrow.png"))); // NOI18N
        label_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_backMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        jLabel11.setText("Tambah");

        jLabel12.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        jLabel12.setText("Hapus");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setText("Diskon");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(label_add)
                                .addGap(87, 87, 87)
                                .addComponent(label_del))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(label_back)
                                .addGap(348, 348, 348)
                                .addComponent(jLabel6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel12)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_back)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_add)
                    .addComponent(label_del))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
        mulai.setDateFormatString("dd/MM/yyyy");
        akhir.setDateFormatString("dd/MM/yyyy");
        
        if (txt_nama.getText().equals("")||txt_besar.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Lengkapi semua data!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            String query = "INSERT INTO tb_diskon(diskon, jumlah, tgl_mulai, tgl_akhir) VALUES (?,?,?,?)";
            String nama = txt_nama.getText();
            String besar = txt_besar.getText();
            String date_mulai = dateFormat.format(mulai.getDate());
            String date_akhir = dateFormat2.format(akhir.getDate());
            try{
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1,nama);
                st.setString(2,besar);
                st.setString(3,date_mulai);
                st.setString(4,date_akhir);

                st.executeUpdate();
                JOptionPane.showMessageDialog(this,"Diskon Ditambahkan!","Success",JOptionPane.PLAIN_MESSAGE);
                tampil();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,"Gagal Menambahkan Data! "+e,"Failed",JOptionPane.ERROR_MESSAGE); 
            }
        }
        tampil();        // TODO add your handling code here:
    }//GEN-LAST:event_label_addMouseClicked

    private void label_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_delMouseClicked
        if (TableData.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this,"Data Belum Dipilih!!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            int dial = JOptionPane.YES_NO_OPTION;
            int dialog = JOptionPane.showConfirmDialog(this,"Apakah Anda Yakin Menghapus?");
            if (dialog == JOptionPane.YES_OPTION) {
                String id = model.getValueAt(TableData.getSelectedRow(),0).toString();
                String sql = "Delete FROM tb_diskon WHERE id_diskon='"+id+"'";
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
            java.util.logging.Logger.getLogger(Diskon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Diskon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Diskon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diskon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Diskon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableData;
    private com.toedter.calendar.JDateChooser akhir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_add;
    private javax.swing.JLabel label_back;
    private javax.swing.JLabel label_del;
    private com.toedter.calendar.JDateChooser mulai;
    private javax.swing.JTextField txt_besar;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
