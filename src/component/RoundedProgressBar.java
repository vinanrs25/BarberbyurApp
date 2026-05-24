/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;
import java.awt.*;
import javax.swing.JProgressBar;

/**
 *
 * @author marwa
 */
public class RoundedProgressBar extends JProgressBar {

    public RoundedProgressBar() {

        setOpaque(false);

        setBorderPainted(false);

        setStringPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        int width = getWidth();
        int height = getHeight();

        // background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, height, height);

        // progress — minimal height agar tetap terlihat
        int progressWidth = Math.max(height, (int)(width * getPercentComplete()));

        g2.setColor(getForeground());
        g2.fillRoundRect(0, 0, progressWidth, height, height, height);

        g2.dispose();
    }
}