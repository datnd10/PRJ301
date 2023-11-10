/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dac Dat
 */
public class Cart {

    private int user_id;
    private int product_color_id;
    private int quantity;

    public Cart() {
    }

    public Cart(int user_id, int product_color_id, int quantity) {
        this.user_id = user_id;
        this.product_color_id = product_color_id;
        this.quantity = quantity;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_color_id() {
        return product_color_id;
    }

    public void setProduct_color_id(int product_color_id) {
        this.product_color_id = product_color_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" + "user_id=" + user_id + ", product_color_id=" + product_color_id + ", quantity=" + quantity + '}';
    }
    
    
}
