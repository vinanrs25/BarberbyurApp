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
public class ActionTransaksiEditor extends AbstractCellEditor implements TableCellEditor {

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column
    ) {
        ActionTransaksiPanel panel = new ActionTransaksiPanel();

        panel.getBtnStruk().addActionListener(e -> {
            fireEditingStopped();
            javax.swing.JOptionPane.showMessageDialog(
                    table,
                    "Struk transaksi baris ke-" + (row + 1),
                    "Struk",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
        });

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(true);
        wrapper.setBackground(component.ThemeColor.SURFACE);
        wrapper.setBorder(javax.swing.BorderFactory.createMatteBorder(
                0, 0, 1, 0, component.ThemeColor.BORDER
        ));
        wrapper.add(panel);
        return wrapper;
    }
}
