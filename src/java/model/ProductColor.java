/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Dac Dat
 */
public class ProductColor {

    private int product_color_id;
    private String color;
    private int quantity;
    private float price;
    private String desciption, image;
    private int product_id;
    private Date create_at;
    private int sold_quantity;

    public ProductColor() {
    }

    public ProductColor(int product_color_id, String color, int quantity, float price, String desciption, String image, int product_id, Date create_at, int sold_quantity) {
        this.product_color_id = product_color_id;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.desciption = desciption;
        this.image = image;
        this.product_id = product_id;
        this.create_at = create_at;
        this.sold_quantity = sold_quantity;
    }

    public int getProduct_color_id() {
        return product_color_id;
    }

    public void setProduct_color_id(int product_color_id) {
        this.product_color_id = product_color_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public int getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(int sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    @Override
    public String toString() {
        return "ProductColor{" + "product_color_id=" + product_color_id + ", color=" + color + ", quantity=" + quantity + ", price=" + price + ", desciption=" + desciption + ", image=" + image + ", product_id=" + product_id + ", create_at=" + create_at + ", sold_quantity=" + sold_quantity + '}';
    } 
}
