
package laundry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class DeleteAdmin extends javax.swing.JFrame {

    Connection conn = Koneksi.getConnection();
    DefaultTableModel model;
    public DeleteAdmin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Delete Admin");
        model = new DefaultTableModel();
        TabelAdmin.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama Admin");
        model.addColumn("Email");      
        model.addColumn("No Telepon");      
        model.addColumn("Alamat");  
        model.setRowCount(0);
        tampil();
    }
    
    private void tampil(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        String sql = "SELECT * FROM tb_pengguna";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Object [] obj = new Object[5];
                obj[0] = rs.getInt("id_pengguna");
                obj[1] = rs.getString("nama");
                obj[2] = rs.getString("email");
                obj[3] = rs.getString("no_telepon");                
                obj[4] = rs.getString("alamat");;
                model.addRow(obj);
            }
            
        }
        catch(SQLException ex){
            System.out.println(ex);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelAdmin = new javax.swing.JTable();
        label_judul = new javax.swing.JLabel();
        label_ket_kembali = new javax.swing.JLabel();
        label_ket_hapus = new javax.swing.JLabel();
        label_back = new javax.swing.JLabel();
        label_del = new javax.swing.JLabel();
        label_icon_judul = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 50));
        jPanel3.setRequestFocusEnabled(false);

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 650));

        TabelAdmin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TabelAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TabelAdmin);

        label_judul.setFont(new java.awt.Font("Shree Devanagari 714", 1, 36)); // NOI18N
        label_judul.setText("Hapus Admin");

        label_ket_kembali.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_kembali.setText("Kembali");

        label_ket_hapus.setFont(new java.awt.Font("Shree Devanagari 714", 3, 14)); // NOI18N
        label_ket_hapus.setText("Hapus Admin");

        label_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/arrow.png"))); // NOI18N
        label_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_backMouseClicked(evt);
            }
        });

        label_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash.png"))); // NOI18N
        label_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_delMouseClicked(evt);
            }
        });

        label_icon_judul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash (1).png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(276, 3));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(label_back)
                                .addGap(118, 118, 118)
                                .addComponent(label_del))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(label_ket_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(label_ket_hapus)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_icon_judul)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_judul)))
                .addContainerGap(403, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(label_judul, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(label_icon_judul)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_back)
                    .addComponent(label_del))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_ket_kembali)
                    .addComponent(label_ket_hapus))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void label_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_backMouseClicked
        Admin ad = new Admin();
        ad.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_label_backMouseClicked

    private void label_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_delMouseClicked
        if (TabelAdmin.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this,"Data Belum Dipilih!!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            int dial = JOptionPane.YES_NO_OPTION;
            int dialog = JOptionPane.showConfirmDialog(this,"Apakah Anda Yakin Menghapus?");
            if (dialog == JOptionPane.YES_OPTION) {
                String id = model.getValueAt(TabelAdmin.getSelectedRow(),0).toString();
                String sql = "Delete FROM tb_pengguna WHERE id_pengguna='"+id+"'";
                try{
                    PreparedStatement st = conn.prepareStatement(sql);
                    st.execute();
                    JOptionPane.showMessageDialog(this,"Terhapus!","Success",JOptionPane.PLAIN_MESSAGE);
                    Admin ad = new Admin(); 
                    ad.setVisible(true);
                    this.setVisible(false);
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
            java.util.logging.Logger.getLogger(DeleteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelAdmin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_back;
    private javax.swing.JLabel label_del;
    private javax.swing.JLabel label_icon_judul;
    private javax.swing.JLabel label_judul;
    private javax.swing.JLabel label_ket_hapus;
    private javax.swing.JLabel label_ket_kembali;
    // End of variables declaration//GEN-END:variables
}
