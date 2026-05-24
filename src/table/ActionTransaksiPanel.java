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
public class ActionTransaksiPanel extends JPanel {
    public JButton btnStruk;

    public ActionTransaksiPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnStruk = createButton("Struk", new Color(60, 60, 70), Color.WHITE);
        add(btnStruk);
    }

    private JButton createButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("SansSerif", Font.BOLD, 11));
        return btn;
    }

    public JButton getBtnStruk() {
        return btnStruk;
    }
}