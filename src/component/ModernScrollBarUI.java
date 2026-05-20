/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author marwa
 */
public class ModernScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {

        thumbColor = ThemeColor.GOLD;

        trackColor = ThemeColor.SURFACE;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {

        JButton button = new JButton();

        button.setPreferredSize(new Dimension(0,0));

        button.setMinimumSize(new Dimension(0,0));

        button.setMaximumSize(new Dimension(0,0));

        return button;
    }

    @Override
    protected void paintThumb(
            Graphics g,
            JComponent c,
            Rectangle thumbBounds
    ) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(thumbColor);

        g2.fillRoundRect(
                thumbBounds.x + 4,
                thumbBounds.y,
                thumbBounds.width - 8,
                thumbBounds.height,
                10,
                10
        );

        g2.dispose();
    }

    @Override
    protected void paintTrack(
            Graphics g,
            JComponent c,
            Rectangle trackBounds
    ) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(trackColor);

        g2.fillRect(
                trackBounds.x,
                trackBounds.y,
                trackBounds.width,
                trackBounds.height
        );

        g2.dispose();
    }
}