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
public class TypeTableCellRenderer
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

        String type = value.toString();

        RoundedBadge badge;

        if (type.equalsIgnoreCase("Layanan")) {

            badge = new RoundedBadge(
                    "LAYANAN",
                    new Color(58, 47, 20),
                    new Color(212, 175, 55)
            );

        } else {

            badge = new RoundedBadge(
                    "PRODUK",
                    new Color(20, 40, 68),
                    new Color(80, 170, 255)
            );
        }

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

        panel.add(badge);

        return panel;
    }
}
