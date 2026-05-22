/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author marwa
 */
public class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column
    ) {
        ActionPanel panel = new ActionPanel();

        panel.setBorder(BorderFactory.createMatteBorder(
            0, 0, 1, 0, component.ThemeColor.BORDER
        ));

        // tombol hapus
        panel.getBtnDelete().addActionListener(e -> {
            fireEditingStopped();
            String noHp = table.getValueAt(row, 1).toString();
            try {
                java.sql.Connection conn = form.Koneksi.getKoneksi();
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "SELECT id FROM pelanggan WHERE no_hp = ?"
                );
                ps.setString(1, noHp);
                java.sql.ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    dialog.HapusPelangganDialog hapus = new dialog.HapusPelangganDialog(
                        (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(table),
                        true, id
                    );
                    hapus.setLocationRelativeTo(null);
                    hapus.setVisible(true);
                    java.awt.Container c = table.getParent();
                    while (c != null) {
                        if (c instanceof form.Pelanggan) {
                            ((form.Pelanggan) c).refreshTable();
                            break;
                        }
                        c = c.getParent();
                    }
                }
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
            }
        });

        // tombol edit
        panel.getBtnEdit().addActionListener(e -> {
            fireEditingStopped();
            String noHp = table.getValueAt(row, 1).toString();
            try {
                java.sql.Connection conn = form.Koneksi.getKoneksi();
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "SELECT id FROM pelanggan WHERE no_hp = ?"
                );
                ps.setString(1, noHp);
                java.sql.ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    dialog.EditPelangganDialog edit = new dialog.EditPelangganDialog(
                        (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(table),
                        true, id
                    );
                    edit.setLocationRelativeTo(null);
                    edit.setVisible(true);
                    java.awt.Container c = table.getParent();
                    while (c != null) {
                        if (c instanceof form.Pelanggan) {
                            ((form.Pelanggan) c).refreshTable();
                            break;
                        }
                        c = c.getParent();
                    }
                }
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
            }
        });
        
        // tombol detail
        panel.getBtnDetail().addActionListener(e -> {
            fireEditingStopped();
            String noHp = table.getValueAt(row, 1).toString();
            try {
                java.sql.Connection conn = form.Koneksi.getKoneksi();
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "SELECT id FROM pelanggan WHERE no_hp = ?"
                );
                ps.setString(1, noHp);
                java.sql.ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    dialog.DetailPelangganDialog detail = new dialog.DetailPelangganDialog(
                        (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(table),
                        true, id
                    );
                    detail.setLocationRelativeTo(null);
                    detail.setVisible(true);
                }
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
            }
        });

        return panel;
    }
}
