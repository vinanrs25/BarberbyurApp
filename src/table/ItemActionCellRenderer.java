/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import component.ThemeColor;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author rhmnsae
 */
public class ItemActionCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
    ) {

        ActionPanel actionPanel = new ActionPanel();

        // hide detail
        actionPanel.getBtnDetail().setVisible(false);

        JPanel panel = new JPanel(
                new GridBagLayout()
        );

        panel.setOpaque(true);

        panel.setBackground(
                ThemeColor.SURFACE
        );

        panel.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0,
                ThemeColor.BORDER
        ));

        panel.add(actionPanel);

        return panel;
    }
}