/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author ASUS
 */
public class koneksi {
        Connection con;

        public koneksi(){
        String id, pass, url, driver;
        id= "root";
        pass="";
        url="jdbc:mysql://localhost:3306/barberbyur";
        driver="com.mysql.cj.jdbc.Driver";
        
    try {
        Class.forName(driver).newInstance();
        con = DriverManager.getConnection(url,id,pass);
        if (con == null){
            System.out.println("koneksi gagal");
        } else {
            System.out.println("koneksi berhasil");
        }
    } catch(Exception e){
            System.out.println("Error :" + e.getMessage());
        }
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        koneksi kon = new koneksi();
    }
    
}
