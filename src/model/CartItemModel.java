/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asus_
 */
// Class untuk Item yang masuk Keranjang
public class CartItemModel {
    public Item produk;
    public int qty;

    public CartItemModel(Item produk, int qty) {
        this.produk = produk;
        this.qty = qty;
    }
}

