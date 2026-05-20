/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author marwa
 */
public class ModernTable extends JTable {
        private int hoveredRow = -1;

    public ModernTable() {
        setBackground(ThemeColor.SURFACE);

        setForeground(ThemeColor.TEXT);

        setGridColor(ThemeColor.BORDER);

        setRowHeight(40);           

        setFont(new Font("SansSerif", Font.PLAIN, 13));

//        setSelectionBackground(ThemeColor.GOLD);
//
//        setSelectionForeground(Color.BLACK);

        setShowVerticalLines(false);

        setIntercellSpacing(new Dimension(0, 1));

        JTableHeader header = getTableHeader();

        header.setBackground(ThemeColor.SURFACE_2);

        header.setForeground(ThemeColor.TEXT_MUTED);

        header.setFont(new Font("SansSerif", Font.BOLD, 12));

        header.setBorder(null);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
    ) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column
        );

        label.setBackground(ThemeColor.SURFACE_2);

        label.setForeground(ThemeColor.TEXT_MUTED);

        label.setFont(new Font("SansSerif", Font.BOLD, 13));

        label.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10)
        );

        return label;
        
    }
});
        
setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column
    ) {
        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column
        );
        
        if (row == hoveredRow) {
            c.setBackground(ThemeColor.GOLD);
            c.setForeground(Color.BLACK);
        } else {
            c.setBackground(ThemeColor.SURFACE);
            c.setForeground(ThemeColor.TEXT);
        }
        
        // border bawah saja untuk isi tabel
        ((JLabel) c).setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, ThemeColor.BORDER),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
        return c;
    }
});

header.setDefaultRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
            
    ) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column
                
        );
        label.setBackground(ThemeColor.SURFACE_2);
        label.setForeground(ThemeColor.TEXT_MUTED);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        // padding sama dengan cell renderer
        label.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(1, 0, 1, 0, ThemeColor.BORDER),
        BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        // alignment eksplisit
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;        
    }
        });
    } 

    public int getHoveredRow() {
    return hoveredRow;
}
}