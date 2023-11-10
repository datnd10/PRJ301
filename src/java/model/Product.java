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
public class Product {

    private int product_id;
    private String name, description;
    private int category_id;
    private Date creat_at;
    private int star;

    public Product() {
    }

    public Product(int product_id, String name, String description, int category_id, Date creat_at, int star) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.creat_at = creat_at;
        this.star = star;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Date getCreat_at() {
        return creat_at;
    }

    public void setCreat_at(Date creat_at) {
        this.creat_at = creat_at;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", name=" + name + ", description=" + description + ", category_id=" + category_id + ", creat_at=" + creat_at + ", star=" + star + '}';
    }

    
}
