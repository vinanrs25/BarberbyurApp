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
 * @author marwa
 */
public class TierTableCellRenderer
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
    String tier = value.toString();
    RoundedBadge badge;
    switch (tier) {
        case "Gold":
            badge = new RoundedBadge("GOLD",
                    new Color(201, 168, 76), Color.BLACK);
            break;
        case "Silver":
            badge = new RoundedBadge("SILVER",
                    new Color(160, 160, 160), Color.BLACK);
            break;
        default:
            badge = new RoundedBadge("BRONZE",
                    new Color(120, 75, 40), Color.WHITE);
            break;
    }

    // bungkus badge dalam JPanel agar bisa diatur posisinya
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setOpaque(true);

    int hoveredRow = -1;
    if (table instanceof component.ModernTable) {
        hoveredRow = ((component.ModernTable) table).getHoveredRow();
    }

    panel.setBackground(
        row == hoveredRow ? ThemeColor.GOLD : ThemeColor.SURFACE
    );

    panel.setBorder(BorderFactory.createMatteBorder(
        0, 0, 1, 0, ThemeColor.BORDER
    ));

    GridBagConstraints gbc = new GridBagConstraints();
    panel.add(badge, gbc);

    return panel;
    }  
    
}