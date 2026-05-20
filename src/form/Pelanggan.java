/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import component.ThemeColor;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rhmnsae
 */
public class Pelanggan extends javax.swing.JPanel {

    /**
     * Creates new form Pelanggan
     */
    public Pelanggan() {
        initComponents();
        //block edit double click
        tablePelanggan.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String[] {
            "Pelanggan", "No. HP", "Total Kunjungan", "Tier", "Kunjungan Terakhir", "Aksi"
        }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        });
        //custom search bar
        txtSearch.setPreferredSize(new java.awt.Dimension(250, 40));
        txtSearch.setHint("Cari pelanggan...");
        
        //custom header tabel
        tablePelanggan.getTableHeader().setOpaque(false);

        tablePelanggan.getTableHeader().setBackground(
        ThemeColor.SURFACE_2
        );

        tablePelanggan.getTableHeader().setForeground(
        ThemeColor.TEXT_MUTED
        );

        tablePelanggan.getTableHeader().setFont(
        new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13)
        );

        tablePelanggan.getTableHeader().setBorder(null);
        
        //custom scrollbar
        scrollTable.getVerticalScrollBar().setUI(
        new component.ModernScrollBarUI()
        );

        scrollTable.getVerticalScrollBar().setPreferredSize(
        new java.awt.Dimension(10,0)
        );
        
        // costum color
        cbTier.setBackground(
        ThemeColor.SURFACE_2
        );
        tableContainer.setBackground(
        ThemeColor.SURFACE
        );
        
        //set radius tabel
        tableContainer.setRadius(20);
        
        //padding pada table scroll bar
        scrollTable.setBorder(
        javax.swing.BorderFactory.createEmptyBorder()
        );

        tableContainer.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(
                15, 15, 15, 15
        )
        );
        
        //menghapus border tabel
        tablePelanggan.setShowGrid(false);
        
        
        //custom dropdown
        cbTier.setFocusable(false);

        cbTier.setBorder(null);

        cbTier.setBackground(
        ThemeColor.BACKGROUND
        );

        cbTier.setForeground(
        ThemeColor.TEXT
        );
        
        //scrollbar transparan dan berwarna hitam
        scrollTable.getViewport().setBackground(
        ThemeColor.SURFACE
        );

        scrollTable.setBackground(
        ThemeColor.SURFACE
        );
        
        //menghilangkan border default dari scroll pane
        scrollTable.setBorder(null);
        
        //load data dummy
        loadTable();
        
        //off switch kolom
        tablePelanggan.getTableHeader().setReorderingAllowed(false);
        
        //fix width kolom
        tablePelanggan.getTableHeader().setResizingAllowed(false);
        tablePelanggan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        int[] widths = {260, 150, 150, 120, 160, 287};

        for (int i = 0; i < widths.length; i++) {
            tablePelanggan.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
            tablePelanggan.getColumnModel().getColumn(i).setMinWidth(widths[i]);
            tablePelanggan.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
        }
        
        //tier dan aksi
        tablePelanggan.getColumnModel()
        .getColumn(3)
        .setCellRenderer(
                new table.TierTableCellRenderer()
        );

        tablePelanggan.getColumnModel()
        .getColumn(5)
        .setCellRenderer(
                new table.ActionCellRenderer()
        );

        tablePelanggan.getColumnModel()
        .getColumn(5)
        .setCellEditor(
                new table.ActionCellEditor()
        );
        
        cbTier.setRenderer(new javax.swing.DefaultListCellRenderer() {

        @Override
        public java.awt.Component getListCellRendererComponent(
                javax.swing.JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus
        ) {

        java.awt.Component c =
                super.getListCellRendererComponent(
                        list,
                        value,
                        index,
                        isSelected,
                        cellHasFocus
                );

        if (isSelected) {

            c.setBackground(ThemeColor.GOLD);

            c.setForeground(Color.BLACK);

        } else {

            c.setBackground(ThemeColor.SURFACE_2);

            c.setForeground(ThemeColor.TEXT);
        }

        return c;
        }
    });

    }  
    
     private void loadTable() {

        DefaultTableModel model =
                (DefaultTableModel) tablePelanggan.getModel();

        model.addRow(new Object[]{
        "Usep",
        "08123456789",
        "15",
        "Gold",
        "10 Mei 2026",
        ""
        });

        model.addRow(new Object[]{
        "Rizky",
        "0812121212",
        "5",
        "Silver",
        "10 Mei 2026",
        ""
        });
         
        model.addRow(new Object[]{
        "Ridho",
        "0819998812",
        "20",
        "Bronze",
        "10 Mei 2026",
        ""
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

        txtSearch = new component.ModernTextField();
        cbTier = new javax.swing.JComboBox<>();
        btnTambah = new component.ModernButton();
        tableContainer = new component.RoundedPanel();
        scrollTable = new javax.swing.JScrollPane();
        tablePelanggan = new component.ModernTable();

        setBackground(new java.awt.Color(10, 10, 11));

        txtSearch.setPreferredSize(new java.awt.Dimension(250, 40));
        txtSearch.addActionListener(this::txtSearchActionPerformed);

        cbTier.setForeground(new java.awt.Color(255, 255, 255));
        cbTier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Tier", "Bronze", "Silver", "Gold" }));
        cbTier.setPreferredSize(new java.awt.Dimension(140, 40));
        cbTier.addActionListener(this::cbTierActionPerformed);

        btnTambah.setText("+ Tambah Pelanggan");

        tablePelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pelanggan", "No. HP", "Total Kunjungan", "Tier", "Kunjungan Terakhir", "Aksi"
            }
        ));
        scrollTable.setViewportView(tablePelanggan);

        javax.swing.GroupLayout tableContainerLayout = new javax.swing.GroupLayout(tableContainer);
        tableContainer.setLayout(tableContainerLayout);
        tableContainerLayout.setHorizontalGroup(
            tableContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE)
        );
        tableContainerLayout.setVerticalGroup(
            tableContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbTier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cbTierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTierActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbTierActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ModernButton btnTambah;
    private javax.swing.JComboBox<String> cbTier;
    private javax.swing.JScrollPane scrollTable;
    private component.RoundedPanel tableContainer;
    private component.ModernTable tablePelanggan;
    private component.ModernTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
