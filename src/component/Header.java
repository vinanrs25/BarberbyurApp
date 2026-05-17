
package component;


public class Header extends javax.swing.JPanel {

    
    public Header() {
        initComponents();
    }
    
    public void setMenuName(String name) {
        lbNameHeader.setText(name);
    }

     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNameHeader = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 22, 26));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(136, 136, 152)));
        setPreferredSize(new java.awt.Dimension(0, 45));
        setRequestFocusEnabled(false);

        lbNameHeader.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbNameHeader.setForeground(new java.awt.Color(240, 237, 232));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbNameHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNameHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbNameHeader;
    // End of variables declaration//GEN-END:variables
}
