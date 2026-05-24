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

public class KomisiCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Color bg = (row % 2 == 0) ? ThemeColor.SURFACE : ThemeColor.SURFACE_2;
        setBackground(new Color(0x16161A));
        setOpaque(true);
        setForeground(new Color(0xB8860B));
        setFont(new Font("SansSerif", Font.BOLD, 12));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ThemeColor.BORDER));

        int kapsterPct = 0;
        if (value instanceof Integer) {
            kapsterPct = (Integer) value;
        } else if (value != null) {
            try {
                kapsterPct = Integer.parseInt(value.toString().trim());
            } catch (NumberFormatException ignored) {
            }
        }
        setText(kapsterPct + "% | " + (100 - kapsterPct) + "% owner");
        return this;
    }
}