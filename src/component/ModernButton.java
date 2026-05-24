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
    private Color normalColor;
    private Color hoverColor;
    private Color borderColor = null;
    private boolean isHovered = false;
    private Color baseColor; 

    public ModernButton() {
        this(ThemeColor.GOLD, ThemeColor.GOLD_HOVER, Color.BLACK);
    }

    public ModernButton(Color normal, Color hover, Color fg) {
        this.normalColor = normal;
        this.hoverColor = hover;
        this.baseColor = ThemeColor.GOLD; // default gold
        this.normalColor = baseColor;
        this.hoverColor = ThemeColor.GOLD_HOVER;
    
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(fg);
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
            addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false; // jangan set normalColor di sini
                repaint();
            }
        });

    }

    // set variant outline (background gelap + border)
    public void setOutlineVariant() {
        this.baseColor = ThemeColor.BACKGROUND;
        this.normalColor = ThemeColor.BACKGROUND;
        this.hoverColor = ThemeColor.SURFACE_2;
        this.borderColor = ThemeColor.BORDER;
        setForeground(ThemeColor.TEXT);
        repaint();
    }   
 
    // set variant outline (background surface+ border)   
    public void setSurfaceVariant() {
        this.baseColor = ThemeColor.SURFACE;
        this.normalColor = ThemeColor.SURFACE;
        this.hoverColor = ThemeColor.SURFACE_2;
        this.borderColor = ThemeColor.BORDER;
        setForeground(ThemeColor.TEXT);
        repaint();
    }

    // set warna custom
    public void setVariant(Color normal, Color hover, Color fg, Color border) {
        this.normalColor = normal;
        this.hoverColor = hover;
        this.borderColor = border;
        setForeground(fg);
        repaint();
    }
    
    //btn Hapus
    public void setDangerVariant() {
        this.baseColor = ThemeColor.DANGER;
        this.normalColor = ThemeColor.DANGER;
        this.hoverColor = ThemeColor.DANGER.darker();
        this.borderColor = null;
        setForeground(Color.WHITE);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // gambar background
        g2.setColor(isHovered ? hoverColor : normalColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

        // gambar border jika ada
        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 16, 16);
        }

        super.paintComponent(g);
        g2.dispose();
    }
    
    
}