/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import component.ThemeColor;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rhmnsae
 */
public class StockTableCellRenderer
        extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
    ) {

        String stok = value.toString();

        JPanel panel = new JPanel(
                new GridBagLayout()
        );

        panel.setOpaque(true);

        int hoveredRow = -1;

        if (table instanceof component.ModernTable) {

            hoveredRow =
                    ((component.ModernTable) table)
                            .getHoveredRow();
        }

        panel.setBackground(
                row == hoveredRow
                ? ThemeColor.GOLD
                : ThemeColor.SURFACE
        );

        panel.setBorder(
                BorderFactory.createMatteBorder(
                        0,0,1,0,ThemeColor.BORDER
                )
        );

        if (stok.contains("pcs")) {
            // cek apakah stok <= 3
            try {
                int stokInt = Integer.parseInt(stok.replace(" pcs", "").trim());
                if (stokInt <= 3) {
                    RoundedBadge badge = new RoundedBadge(
                            "⚠ Stok: " + stok,
                            new Color(127, 29, 29),
                            new Color(252, 165, 165)
                    );
                    panel.add(badge);
                } else {
                    JLabel label = new JLabel(stok);
                    label.setForeground(ThemeColor.TEXT);
                    panel.add(label);
                }
            } catch (NumberFormatException ex) {
                JLabel label = new JLabel(stok);
                label.setForeground(ThemeColor.TEXT);
                panel.add(label);
            }
        } else {
            // durasi layanan — tampil biasa
            JLabel label = new JLabel(stok);
            label.setForeground(ThemeColor.TEXT);
            panel.add(label);
        }

        return panel;
    }
}