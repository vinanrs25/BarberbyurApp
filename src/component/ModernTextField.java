/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author marwa
 */

public class ModernTextField extends JTextField {

    private String hint = "";

    public ModernTextField() {

        setOpaque(false);

        setBorder(new EmptyBorder(10, 15, 10, 15));

        setBackground(ThemeColor.SURFACE_2);
        
        setForeground(ThemeColor.TEXT);

        setCaretColor(ThemeColor.TEXT);

        setFont(new Font("SansSerif", Font.PLAIN, 14));
    }

    public void setHint(String hint) {

        this.hint = hint;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // background rounded
        g2.setColor(ThemeColor.SURFACE_2);

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                15,
                15
        );

        // text asli input
        super.paintComponent(g);

        // placeholder
        if (getText().isEmpty()) {

            g2.setColor(ThemeColor.TEXT_MUTED);

            g2.drawString(
                    hint,
                    15,
                    getHeight() / 2 + 5
            );
        }

        g2.dispose();
    }
}
