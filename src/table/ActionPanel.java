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
public class ActionPanel extends JPanel {

    public JButton btnDetail;
    public JButton btnEdit;
    public JButton btnDelete;

    public ActionPanel() {

        setOpaque(false);

        setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        5,
                        5
                )
        );

        btnDetail = createButton(
                "Detail",
                new Color(60,60,70),
                Color.WHITE
        );

        btnEdit = createButton(
                "Edit",
                new Color(201,168,76),
                Color.BLACK
        );

        btnDelete = createButton(
                "Hapus",
                new Color(224,85,85),
                Color.WHITE
        );

        add(btnDetail);

        add(btnEdit);

        add(btnDelete);
    }

    private JButton createButton(
            String text,
            Color bg,
            Color fg
    ) {

        JButton btn = new JButton(text);

        btn.setFocusPainted(false);

        btn.setBorderPainted(false);

        btn.setBackground(bg);

        btn.setForeground(fg);

        btn.setFont(
                new Font("SansSerif", Font.BOLD, 11)
        );

        return btn;
    }
    
    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDetail() {
        return btnDetail;
    }
}