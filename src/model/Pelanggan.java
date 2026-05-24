/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asus_
 */
public class Pelanggan {
    public int id;
    public String nama;
    public String no_hp;
    public int kunjungan;
    public int poin;

    public Pelanggan(int id, String nama, String no_hp, int kunjungan, int poin) {
        this.id = id;
        this.nama = nama;
        this.no_hp = no_hp;
        this.kunjungan = kunjungan;
        this.poin = poin;
    }
}