/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dialog;

import component.ThemeColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import form.Koneksi;
import component.RoundedPanel;
/**
 *
 * @author marwa
 */
public class DetailPelangganDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(DetailPelangganDialog.class.getName());

    private int idPelanggan;

    public DetailPelangganDialog(java.awt.Frame parent, boolean modal, int idPelanggan) {
        super(parent, modal);
        this.idPelanggan = idPelanggan;
        initComponents();
        mainPanel.setRadius(20);
        mainPanel.setBackground(ThemeColor.BACKGROUND);
        panelRiwayat.setBackground(ThemeColor.BACKGROUND);
        labelJudul.setForeground(ThemeColor.TEXT);
        ((component.RoundedPanel) cardPoin).setRadius(20);
        ((component.RoundedPanel) cardKunjungan).setRadius(20);
        ((component.RoundedPanel) cardBelanja).setRadius(20);
        panelRiwayat.setBackground(ThemeColor.BACKGROUND);
        scrollRiwayat.setBorder(null);
        scrollRiwayat.getViewport().setBackground(ThemeColor.BACKGROUND);
        scrollRiwayat.getVerticalScrollBar().setUI(new component.ModernScrollBarUI());
        scrollRiwayat.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(8, 0));
        loadDetailPelanggan();
    }

        private void loadDetailPelanggan() {
        try {
            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(
                "SELECT nama, no_hp, point, total_kunjungan, tier FROM pelanggan WHERE id = ?"
            );
            ps.setInt(1, idPelanggan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("nama");
                String noHp = rs.getString("no_hp");
                String tier = rs.getString("tier");
                if (tier == null)
                    tier = "Bronze";
                
                namaPelanggan.setText(nama);
                namaPelanggan.setForeground(ThemeColor.TEXT);

                // tentukan warna tier
                String tierColor;
                switch (tier) {
                    case "Gold":
                        tierColor = "#C9A84C";
                        break;
                    case "Silver":
                        tierColor = "#A0A0A0";
                        break;
                    case "Platinum":
                        tierColor = "#64C8DC";
                        break;
                    default: // Bronze
                        tierColor = "#B47828";
                        break;
                }

                // no hp putih + tier berwarna dalam satu label pakai HTML
                noHP.setText(
                    "<html><center>" +
                    "<span style='color:#8C8C96'>" + noHp + " " + "|" + "</span>" +
                    "&nbsp;&nbsp;" +
                    "<span style='color:" + tierColor + "'>" + tier.toUpperCase() + "</span>" +
                    "</center></html>"
                );

                jLabel1.setText(String.valueOf(rs.getInt("point")));
                jLabel2.setText(String.valueOf(rs.getInt("total_kunjungan")));
            }

                rs.close();
                ps.close();

                // load total belanja
                PreparedStatement ps2 = conn.prepareStatement(
                    "SELECT COALESCE(SUM(total), 0) as total_belanja FROM transaksi WHERE id_pelanggan = ?"
                );
                ps2.setInt(1, idPelanggan);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    int totalBelanja = rs2.getInt("total_belanja");
                    jLabel3.setText(String.format("Rp%,d", totalBelanja).replace(",", "."));
                }
                rs2.close();
                ps2.close();

                // load riwayat transaksi
                PreparedStatement ps3 = conn.prepareStatement(
                    "SELECT t.tanggal, GROUP_CONCAT(i.nama SEPARATOR ', ') as layanan, t.total " +
                    "FROM transaksi t " +
                    "JOIN detail_transaksi dt ON t.id = dt.id_transaksi " +
                    "JOIN item i ON dt.id_item = i.id " +
                    "WHERE t.id_pelanggan = ? " +
                    "GROUP BY t.id " +
                    "ORDER BY t.tanggal DESC"
                );
                ps3.setInt(1, idPelanggan);
                ResultSet rs3 = ps3.executeQuery();

                panelRiwayat.removeAll();
                while (rs3.next()) {
                    String tanggal = rs3.getDate("tanggal").toString();
                    String layanan = rs3.getString("layanan");
                    String harga = String.format("Rp%,d", rs3.getInt("total")).replace(",", ".");
                    panelRiwayat.add(createItem(tanggal, layanan, harga));
                }
                panelRiwayat.revalidate();
                panelRiwayat.repaint();

                rs3.close();
                ps3.close();

            } catch (SQLException e) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Gagal load data: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        }


    private javax.swing.JPanel createItem(String tanggal, String layanan, String harga) {
        javax.swing.JPanel item = new javax.swing.JPanel(new java.awt.BorderLayout());
        item.setBackground(ThemeColor.BACKGROUND);
        item.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 50));
        item.setPreferredSize(new java.awt.Dimension(700, 50));
        item.setBorder(javax.swing.BorderFactory.createMatteBorder(
            0, 0, 1, 0, new java.awt.Color(40, 40, 40)
        ));

        javax.swing.JPanel left = new javax.swing.JPanel(
            new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 15)
        );
        left.setOpaque(false);

        javax.swing.JLabel lbTanggal = new javax.swing.JLabel(tanggal);
        lbTanggal.setForeground(ThemeColor.TEXT_MUTED);

        javax.swing.JLabel lbLayanan = new javax.swing.JLabel(layanan);
        lbLayanan.setForeground(ThemeColor.TEXT);
        lbLayanan.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));

        left.add(lbTanggal);
        left.add(lbLayanan);

        javax.swing.JLabel lbHarga = new javax.swing.JLabel(harga);
        lbHarga.setForeground(ThemeColor.GOLD);
        lbHarga.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
        lbHarga.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 15));

        item.add(left, java.awt.BorderLayout.WEST);
        item.add(lbHarga, java.awt.BorderLayout.EAST);

        return item;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new component.RoundedPanel();
        labelJudul = new javax.swing.JLabel();
        namaPelanggan = new javax.swing.JLabel();
        noHP = new javax.swing.JLabel();
        cardPoin = new RoundedPanel();
        totalPoin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cardKunjungan = new RoundedPanel();
        totalPoin1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cardBelanja = new RoundedPanel();
        totalPoin2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        scrollRiwayat = new javax.swing.JScrollPane();
        panelRiwayat = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detail Pelanggan");
        setResizable(false);

        labelJudul.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        labelJudul.setText("Detail Pelanggan");

        namaPelanggan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        namaPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namaPelanggan.setText("jLabel1");

        noHP.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        noHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noHP.setText("jLabel1");

        cardPoin.setBackground(new java.awt.Color(22, 22, 26));

        totalPoin.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        totalPoin.setForeground(new java.awt.Color(136, 136, 152));
        totalPoin.setText("Total Poin");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(201, 168, 76));
        jLabel1.setText("jLabel1");

        jPanel1.setBackground(new java.awt.Color(201, 168, 76));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardPoinLayout = new javax.swing.GroupLayout(cardPoin);
        cardPoin.setLayout(cardPoinLayout);
        cardPoinLayout.setHorizontalGroup(
            cardPoinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPoinLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(cardPoinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalPoin, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardPoinLayout.setVerticalGroup(
            cardPoinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPoinLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(totalPoin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(27, 27, 27))
        );

        cardKunjungan.setBackground(new java.awt.Color(22, 22, 26));

        totalPoin1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        totalPoin1.setForeground(new java.awt.Color(136, 136, 152));
        totalPoin1.setText("Kunjungan");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel1");

        jPanel2.setBackground(new java.awt.Color(85, 153, 221));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardKunjunganLayout = new javax.swing.GroupLayout(cardKunjungan);
        cardKunjungan.setLayout(cardKunjunganLayout);
        cardKunjunganLayout.setHorizontalGroup(
            cardKunjunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardKunjunganLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(cardKunjunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalPoin1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardKunjunganLayout.setVerticalGroup(
            cardKunjunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardKunjunganLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(totalPoin1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27))
        );

        cardBelanja.setBackground(new java.awt.Color(22, 22, 26));

        totalPoin2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        totalPoin2.setForeground(new java.awt.Color(136, 136, 152));
        totalPoin2.setText("Total Belanja");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("jLabel1");

        jPanel3.setBackground(new java.awt.Color(76, 175, 130));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardBelanjaLayout = new javax.swing.GroupLayout(cardBelanja);
        cardBelanja.setLayout(cardBelanjaLayout);
        cardBelanjaLayout.setHorizontalGroup(
            cardBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardBelanjaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(cardBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalPoin2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cardBelanjaLayout.setVerticalGroup(
            cardBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardBelanjaLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(totalPoin2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(27, 27, 27))
        );

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Riwayat Transaksi");

        panelRiwayat.setBackground(new java.awt.Color(0, 51, 0));
        panelRiwayat.setLayout(new javax.swing.BoxLayout(panelRiwayat, javax.swing.BoxLayout.Y_AXIS));
        scrollRiwayat.setViewportView(panelRiwayat);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(labelJudul)
                        .addGap(258, 258, 258))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noHP, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(288, 288, 288))))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollRiwayat)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(cardPoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cardKunjungan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cardBelanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelJudul)
                .addGap(28, 28, 28)
                .addComponent(namaPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noHP)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardBelanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardKunjungan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardBelanja;
    private javax.swing.JPanel cardKunjungan;
    private javax.swing.JPanel cardPoin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelJudul;
    private component.RoundedPanel mainPanel;
    private javax.swing.JLabel namaPelanggan;
    private javax.swing.JLabel noHP;
    private javax.swing.JPanel panelRiwayat;
    private javax.swing.JScrollPane scrollRiwayat;
    private javax.swing.JLabel totalPoin;
    private javax.swing.JLabel totalPoin1;
    private javax.swing.JLabel totalPoin2;
    // End of variables declaration//GEN-END:variables
}
