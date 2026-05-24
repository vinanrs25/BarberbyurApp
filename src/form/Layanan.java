/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import component.ThemeColor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dialog.TambahItemDialog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rhmnsae
 */
public class Layanan extends javax.swing.JPanel {

    /**
     * Creates new form Layanan
     */
    
    public Layanan() {
        initComponents();

        setBorder(
                javax.swing.BorderFactory.createEmptyBorder(0, 0, 40, 40)
        );

        tableLayanan.setModel(
                new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "ID", "Nama Item", "Tipe", "Harga", "Stok/Durasi", "Status", "Aksi"
                        }
                ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        }
        );

        // sembunyikan kolom ID
        tableLayanan.getColumnModel().getColumn(0).setMinWidth(0);
        tableLayanan.getColumnModel().getColumn(0).setMaxWidth(0);
        tableLayanan.getColumnModel().getColumn(0).setWidth(0);

        // satu blok renderer — index sudah geser +1 karena ada kolom ID
        tableLayanan.getColumnModel().getColumn(2).setCellRenderer(new table.TypeTableCellRenderer());
        tableLayanan.getColumnModel().getColumn(4).setCellRenderer(new table.StockTableCellRenderer());
        tableLayanan.getColumnModel().getColumn(5).setCellRenderer(new table.StatusTableCellRenderer());
        tableLayanan.getColumnModel().getColumn(6).setCellRenderer(new table.ItemActionCellRenderer());
        tableLayanan.getColumnModel().getColumn(6).setCellEditor(new table.ItemActionCellEditor());

        // search
        txtSearch.setHint("Cari item...");
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                loadData();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                loadData();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
            }
        });

        // header
        tableLayanan.getTableHeader().setOpaque(false);
        tableLayanan.getTableHeader().setBackground(ThemeColor.SURFACE_2);
        tableLayanan.getTableHeader().setForeground(ThemeColor.TEXT_MUTED);
        tableLayanan.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        tableLayanan.getTableHeader().setBorder(null);
        tableLayanan.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 40));

        // table
        tableLayanan.setBackground(ThemeColor.SURFACE);
        tableLayanan.setForeground(ThemeColor.TEXT);
        tableLayanan.setRowHeight(56);
        tableLayanan.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
        tableLayanan.setSelectionBackground(ThemeColor.SURFACE);
        tableLayanan.setSelectionForeground(ThemeColor.TEXT);
        tableLayanan.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableLayanan.setShowGrid(false);

        // scroll
        scrollTable.getVerticalScrollBar().setUI(new component.ModernScrollBarUI());
        scrollTable.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(10, 0));

        // container
        tableContainer.setBackground(ThemeColor.SURFACE);
        tableContainer.setRadius(20);
        tableContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // dropdown
        cbTier.setFocusable(false);
        cbTier.setBorder(null);
        cbTier.setBackground(ThemeColor.BACKGROUND);
        cbTier.setForeground(ThemeColor.TEXT);

        // scrollpane
        scrollTable.getViewport().setBackground(ThemeColor.SURFACE);
        scrollTable.setBackground(ThemeColor.SURFACE);
        scrollTable.setBorder(null);
        
        scrollTable.setCorner(javax.swing.JScrollPane.LOWER_RIGHT_CORNER, new javax.swing.JPanel() {
            {
                setBackground(ThemeColor.SURFACE);
            }
        });
        scrollTable.setCorner(javax.swing.JScrollPane.LOWER_LEFT_CORNER, new javax.swing.JPanel() {
            {
                setBackground(ThemeColor.SURFACE);
            }
        });
        scrollTable.setCorner(javax.swing.JScrollPane.UPPER_RIGHT_CORNER, new javax.swing.JPanel() {
            {
                setBackground(ThemeColor.SURFACE);
            }
        });

        // fix table
        tableLayanan.getTableHeader().setReorderingAllowed(false);
        tableLayanan.getTableHeader().setResizingAllowed(false);
        tableLayanan.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        loadData();
    }

    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) tableLayanan.getModel();
        model.setRowCount(0);

        String keyword = txtSearch.getText().trim();
        String selectedTipe = cbTier.getSelectedItem().toString();

        String tipeFilter = "";
        if (selectedTipe.equals("Layanan (Jasa)")) {
            tipeFilter = "service";
        } else if (selectedTipe.equals("Produk (Barang)")) {
            tipeFilter = "product";
        }

        try {
            Connection conn = Koneksi.getKoneksi();

            String sql = "SELECT * FROM item WHERE nama LIKE ?";
            if (!tipeFilter.isEmpty()) {
                sql += " AND tipe = ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            if (!tipeFilter.isEmpty()) {
                ps.setString(2, tipeFilter);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String deskripsi = rs.getString("deskripsi");
                String tipe = rs.getString("tipe");
                String harga = "Rp " + rs.getString("harga");
                String stokDurasi;

                if (tipe.equals("service")) {
                    tipe = "Layanan";
                    stokDurasi = rs.getString("durasi") + " menit";
                } else {
                    tipe = "Produk";
                    stokDurasi = rs.getInt("stok") + " pcs";
                }

                String status = rs.getString("status");
                String namaHtml = "<html><div style='text-align:center;'>"
                        + "<b>" + nama + "</b><br>"
                        + "<span style='color:gray;'>" + deskripsi + "</span>"
                        + "</div></html>";

                model.addRow(new Object[]{id, namaHtml, tipe, harga, stokDurasi, status, ""});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        btnTambah = new component.ModernButton();
        tableContainer = new component.RoundedPanel();
        scrollTable = new javax.swing.JScrollPane();
        tableLayanan = new component.ModernTable();
        txtSearch = new component.ModernTextField();
        cbTier = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(10, 10, 11));

        btnTambah.setText("+ Tambah Item");
        btnTambah.addActionListener(this::btnTambahActionPerformed);

        tableContainer.setMaximumSize(new java.awt.Dimension(0, 0));

        scrollTable.setBorder(null);
        scrollTable.setToolTipText("");

        tableLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Item", "Tipe", "Harga", "Stok/Durasi", "Status", "Aksi"
            }
        ));
        scrollTable.setViewportView(tableLayanan);

        javax.swing.GroupLayout tableContainerLayout = new javax.swing.GroupLayout(tableContainer);
        tableContainer.setLayout(tableContainerLayout);
        tableContainerLayout.setHorizontalGroup(
            tableContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTable)
        );
        tableContainerLayout.setVerticalGroup(
            tableContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );

        txtSearch.setPreferredSize(new java.awt.Dimension(250, 40));
        txtSearch.addActionListener(this::txtSearchActionPerformed);

        cbTier.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cbTier.setForeground(new java.awt.Color(255, 255, 255));
        cbTier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Tipe", "Layanan (Jasa)", "Produk (Barang)" }));
        cbTier.setPreferredSize(new java.awt.Dimension(140, 40));
        cbTier.addActionListener(this::cbTierActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbTier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(393, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cbTierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTierActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_cbTierActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:       
        java.awt.Frame parent
                = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);

        TambahItemDialog dialog = new TambahItemDialog(parent, true);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true); 

        loadData();
        
    }//GEN-LAST:event_btnTambahActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ModernButton btnTambah;
    private javax.swing.JComboBox<String> cbTier;
    private javax.swing.JScrollPane scrollTable;
    private component.RoundedPanel tableContainer;
    private component.ModernTable tableLayanan;
    private component.ModernTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
