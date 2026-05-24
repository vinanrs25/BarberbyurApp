/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import dialog.EditItemDialog;
import dialog.HapusItemDialog;

/**
 *
 * @author rhmnsae
 */
public class ItemActionCellEditor
        extends AbstractCellEditor
        implements TableCellEditor {

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column
    ) {

        ActionPanel actionPanel = new ActionPanel();

        // hide detail
        actionPanel.getBtnDetail().setVisible(false);

        // tombol edit
        actionPanel.getBtnEdit().addActionListener(e -> {
            fireEditingStopped();
            try {
                // Ambil ID langsung dari kolom tersembunyi, BUKAN parsing HTML
                int idItem = (int) table.getModel().getValueAt(row, 0);

                java.sql.Connection conn = form.Koneksi.getKoneksi();
                java.sql.PreparedStatement ps = conn.prepareStatement(
                        "SELECT * FROM item WHERE id = ?"
                );
                ps.setInt(1, idItem);
                java.sql.ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String nama = rs.getString("nama");
                    String tipe = rs.getString("tipe");
                    int harga = rs.getInt("harga");
                    String durasi = rs.getString("durasi");
                    int stok = rs.getInt("stok");
                    String status = rs.getString("status");
                    String deskripsi = rs.getString("deskripsi");

                    EditItemDialog edit = new EditItemDialog(
                            (java.awt.Frame) SwingUtilities.getWindowAncestor(table),
                            true,
                            idItem, nama, tipe, harga, durasi, stok, status, deskripsi
                    );
                    edit.setLocationRelativeTo(null);
                    edit.setVisible(true);

                    // Refresh tabel setelah dialog ditutup
                    // Cari panel Layanan dari ancestor table
                    Container parent = table.getParent();
                    while (parent != null) {
                        if (parent instanceof form.Layanan) {
                            ((form.Layanan) parent).loadData();
                            break;
                        }
                        parent = parent.getParent();
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
        
        // tombol hapus
        actionPanel.getBtnDelete().addActionListener(e -> {
            fireEditingStopped();
            try {
                int idItem = (int) table.getModel().getValueAt(row, 0);

                HapusItemDialog hapus = new HapusItemDialog(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(table),
                        true,
                        idItem
                );
                hapus.setLocationRelativeTo(null);
                hapus.setVisible(true);

                // Refresh tabel setelah hapus
                Container parent = table.getParent();
                while (parent != null) {
                    if (parent instanceof form.Layanan) {
                        ((form.Layanan) parent).loadData();
                        break;
                    }
                    parent = parent.getParent();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setOpaque(true);

        panel.setBackground(component.ThemeColor.SURFACE);

        panel.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0,
                component.ThemeColor.BORDER
        ));

        panel.add(actionPanel);

        return panel;
    }
}