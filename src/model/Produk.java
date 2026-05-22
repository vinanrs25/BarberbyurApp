/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asus_
 */
// Class untuk Item dari Database
public class Produk {
    public int id;
    public String nama;
    public int harga;
    public String tipe;
    public String deskripsi;
    public String durasiStok;

    public Produk(int id, String nama, int harga, String tipe, String deskripsi, String durasiStok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.tipe = tipe;
        this.deskripsi = deskripsi;
        this.durasiStok = durasiStok;
    }
}