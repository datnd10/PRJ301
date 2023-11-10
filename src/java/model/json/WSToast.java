/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.json;

/**
 *
 * @author Dac Dat
 */
public class WSToast {
    private String name;
    private int totalItems;
    private int totalPrice;

    public WSToast() {
    }

    public WSToast(String name, int totalItems, int totalPrice) {
        this.name = name;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "WSToast{" + "name=" + name + ", totalItems=" + totalItems + ", totalPrice=" + totalPrice + '}';
    }
    
    
}
