/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dac Dat
 */
public class Shipping {
    private int shipping_id;
    private String type;
    private float price;

    public Shipping() {
    }

    public Shipping(int shipping_id, String type, float price) {
        this.shipping_id = shipping_id;
        this.type = type;
        this.price = price;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shipping{" + "shipping_id=" + shipping_id + ", type=" + type + ", price=" + price + '}';
    }
    
}
