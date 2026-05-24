/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author marwa
 */
public class ActionTransaksiRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column
    ) {
        ActionTransaksiPanel panel = new ActionTransaksiPanel();
        panel.setBorder(javax.swing.BorderFactory.createMatteBorder(
            0, 0, 1, 0, component.ThemeColor.BORDER
        ));
        return panel;
    }
}