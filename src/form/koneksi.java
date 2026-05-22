package form;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asus_
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    private static Connection koneksi;

    public static Connection getKoneksi() {

        if (koneksi == null) {

            try {

                String url = "jdbc:mysql://localhost:3306/barberbyur";
                String user = "root";
                String password = "";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                koneksi = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi Berhasil");

            } catch (SQLException t) {

                System.out.println("Error Membuat Koneksi");
            }
        }

        return koneksi;
    }
}