/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author marwa
 */
public class KapsterProgressBar extends JPanel {
    
    private int value;
    private int maxValue;
    private Color barColor;

    public KapsterProgressBar(int value, int maxValue, Color barColor) {
        this.value = value;
        this.maxValue = maxValue;
        this.barColor = barColor;
        setOpaque(false);
        setPreferredSize(new Dimension(0, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();
        int arc = h;

        // background
        g2.setColor(ThemeColor.SURFACE_2);
        g2.fillRoundRect(0, 0, w, h, arc, arc);

        // bar
        if (maxValue > 0 && value > 0) {
            int barWidth = (int) ((value * 1.0 / maxValue) * w);
            barWidth = Math.max(arc, barWidth); // minimal arc agar tetap rounded
            g2.setColor(barColor);
            g2.fillRoundRect(0, 0, barWidth, h, arc, arc);
        }

        g2.dispose();
    }
}