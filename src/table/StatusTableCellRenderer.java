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
public class StatusTableCellRenderer
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

        String status = value.toString();

        RoundedBadge badge;

        if (status.equalsIgnoreCase("Aktif")) {

            badge = new RoundedBadge(
                    "Aktif",
                    new Color(20, 83, 45),
                    new Color(74, 222, 128)
            );

        } else {

            badge = new RoundedBadge(
                    "Nonaktif",
                    new Color(127, 29, 29),
                    new Color(252, 165, 165)
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
                        0,0,1, 0,ThemeColor.BORDER
                )
        );

        panel.add(badge);

        return panel;
    }
}