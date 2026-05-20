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
public class ActionCellEditor
        extends AbstractCellEditor
        implements TableCellEditor {

    private ActionPanel panel;

    public ActionCellEditor() {

        panel = new ActionPanel();
    }

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
        
    ActionPanel panel = new ActionPanel();
    
    panel.setBorder(BorderFactory.createMatteBorder(
        0, 0, 1, 0, component.ThemeColor.BORDER
    ));

        return panel;
    }
}