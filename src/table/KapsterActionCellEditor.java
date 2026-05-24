/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import dialog.EditKapsterDialog;
import dialog.HapusKapsterDialog;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author rhmnsae
 */
public class KapsterActionCellEditor
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

                int idKapster
                        = (int) table.getModel()
                                .getValueAt(row, 0);

                Connection conn
                        = form.Koneksi.getKoneksi();

                PreparedStatement ps
                        = conn.prepareStatement(
                                "SELECT * FROM kapster WHERE id = ?"
                        );

                ps.setInt(1, idKapster);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    String nama
                            = rs.getString("nama");

                    String noHp
                            = rs.getString("no_hp");

                    String spesialisasi
                            = rs.getString("spesialisasi");

                    int komisi
                            = rs.getInt("komisi_persen");

                    String status
                            = rs.getString("status");

                    EditKapsterDialog edit
                            = new EditKapsterDialog(
                                    (java.awt.Frame) SwingUtilities
                                            .getWindowAncestor(table),
                                    true,
                                    idKapster,
                                    nama,
                                    noHp,
                                    spesialisasi,
                                    komisi,
                                    status
                            );

                    edit.setLocationRelativeTo(null);
                    edit.setVisible(true);

                    // refresh tabel
                    Container parent
                            = table.getParent();

                    while (parent != null) {

                        if (parent instanceof form.Kapster) {

                            ((form.Kapster) parent)
                                    .loadData();

                            break;
                        }

                        parent = parent.getParent();
                    }
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Error: " + ex.getMessage()
                );
            }
        });
        
        // tombol hapus
        actionPanel.getBtnDelete().addActionListener(e -> {

            fireEditingStopped();

            try {

                int idKapster
                        = (int) table.getModel()
                                .getValueAt(row, 0);

                HapusKapsterDialog hapus
                        = new HapusKapsterDialog(
                                (java.awt.Frame) SwingUtilities.getWindowAncestor(table),
                                true,
                                idKapster
                        );

                hapus.setLocationRelativeTo(null);
                hapus.setVisible(true);

                // refresh tabel
                Container parent = table.getParent();

                while (parent != null) {

                    if (parent instanceof form.Kapster) {

                        ((form.Kapster) parent)
                                .loadData();

                        break;
                    }

                    parent = parent.getParent();
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Error: " + ex.getMessage()
                );
            }
        });

        JPanel panel
                = new JPanel(new GridBagLayout());

        panel.setOpaque(true);

        panel.setBackground(
                component.ThemeColor.SURFACE
        );

        panel.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 1, 0,
                        component.ThemeColor.BORDER
                )
        );

        panel.add(actionPanel);

        return panel;
    }
    
}
