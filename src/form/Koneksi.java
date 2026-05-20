package form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection conn;

    public static Connection getKoneksi() {

        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/barberbyur";
                String user = "root";
                String password = "";

                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi Berhasil");

            } catch (ClassNotFoundException e) {
                System.out.println("Driver MySQL tidak ditemukan: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error koneksi database: " + e.getMessage());
            }
        }

        return conn;
    }
}