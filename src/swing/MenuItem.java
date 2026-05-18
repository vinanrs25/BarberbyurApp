 
package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import model.Model_Menu;


public class MenuItem extends javax.swing.JPanel {

    private boolean selected;
    private boolean over;
    private Model_Menu data;
    
    public MenuItem(Model_Menu data) {
        this.data = data;
        initComponents();
        setOpaque(false);
        
        if (data.getType() == Model_Menu.MenuType.MENU) {
            lbIcon.setIcon(data.toIcon());
            lbName.setText(data.getName());
        } else if (data.getType() == Model_Menu.MenuType.TITLE) {
            lbIcon.setText(data.getName());
            lbIcon.setFont(new Font("sansserif", 1, 12));
            lbName.setVisible(false);
        } else {
            lbName.setText(" ");
        }
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        
        if (data.getType() == Model_Menu.MenuType.MENU) {
            if (selected) {
                // name selected
                lbName.setForeground(new Color(201, 168, 76));
                
                // icon selected
                lbIcon.setIcon(new javax.swing.ImageIcon(
                        getClass().getResource("/icon/selected_" + data.getIcon() + ".png")
                ));
            } else {
                // name default
                lbName.setForeground(new Color(136, 136, 152));
                
                // icon default
                lbIcon.setIcon(data.toIcon());
            }
        }
        repaint();
    }

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();

        lbIcon.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbIcon.setForeground(new java.awt.Color(136, 136, 152));

        lbName.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lbName.setForeground(new java.awt.Color(136, 136, 152));
        lbName.setText("Menu Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbIcon)
                .addGap(18, 18, 18)
                .addComponent(lbName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Selected
        if (selected) {

            // Background selected
            g2.setColor(Color.decode("#2C271B"));
            g2.fillRoundRect(8, 4, getWidth() - 16, getHeight() - 8, 16, 16);

        // Hover
        } else if (over) {

            // Background hover transparan
            g2.setColor(new Color(255, 255, 255, 25));
            g2.fillRoundRect(8, 4, getWidth() - 16, getHeight() - 8, 16, 16);
        }

        super.paintComponent(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
