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
public class Review {
    private int review_id;
    private String description;
    private int user_id;
    private int product_color_id;
    private String image;
    private int star;
    private Date create_at;

    public Review() {
    }

    public Review(int review_id, String description, int user_id, int product_color_id, String image, int star, Date create_at) {
        this.review_id = review_id;
        this.description = description;
        this.user_id = user_id;
        this.product_color_id = product_color_id;
        this.image = image;
        this.star = star;
        this.create_at = create_at;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Review{" + "review_id=" + review_id + ", description=" + description + ", user_id=" + user_id + ", product_color_id=" + product_color_id + ", image=" + image + ", star=" + star + ", create_at=" + create_at + '}';
    }
}
