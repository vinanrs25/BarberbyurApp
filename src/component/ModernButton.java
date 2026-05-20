/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author marwa
 */
public class ModernButton extends JButton {

    private Color normalColor = ThemeColor.GOLD;
    private Color hoverColor = ThemeColor.GOLD_HOVER;

    public ModernButton() {

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.BLACK);

        setFont(new Font("SansSerif", Font.BOLD, 14));

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                normalColor = hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                normalColor = ThemeColor.GOLD;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(normalColor);

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                16,
                16
        );

        super.paintComponent(g);

        g2.dispose();
    }
}