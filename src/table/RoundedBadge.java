/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.awt.*;
import javax.swing.*;


/**
 *
 * @author marwa
 */
public class RoundedBadge extends JLabel {

    private Color backgroundColor;

    public RoundedBadge(
            String text,
            Color bg,
            Color fg
    ) {

        super(text);

        this.backgroundColor = bg;

        setForeground(fg);

        setHorizontalAlignment(SwingConstants.CENTER);

        setFont(
                new Font("SansSerif", Font.BOLD, 9)
        );

        setBorder(
            BorderFactory.createEmptyBorder(
                    2,
                    8,
                    2,
                    8
            )
        );
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(backgroundColor);

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                20,
                20
        );

        super.paintComponent(g);

        g2.dispose();
    }
   @Override
    public Dimension getPreferredSize() {
    Dimension size = super.getPreferredSize();
    size.width += 8;
    size.height = Math.max(size.height + 4, 28); // fix dari height = 8
    return size;
}
}