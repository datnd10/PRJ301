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
public class Order {
    private int order_id;
    private int user_id;
    private float total_money;
    private String name, address, phone, message;
    private Date create_at;
    private int status_id;
    private int shipping_id;

    public Order() {
    }

    public Order(int order_id, int user_id, float total_money, String name, String address, String phone, String message, Date create_at, int status_id, int shipping_id) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.total_money = total_money;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.message = message;
        this.create_at = create_at;
        this.status_id = status_id;
        this.shipping_id = shipping_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getTotal_money() {
        return total_money;
    }

    public void setTotal_money(float total_money) {
        this.total_money = total_money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", user_id=" + user_id + ", total_money=" + total_money + ", name=" + name + ", address=" + address + ", phone=" + phone + ", message=" + message + ", create_at=" + create_at + ", status_id=" + status_id + ", shipping_id=" + shipping_id + '}';
    }
}
