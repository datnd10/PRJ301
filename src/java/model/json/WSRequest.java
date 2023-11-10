/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.json;

/**
 *
 * @author Dac Dat
 */
public class WSRequest {
    private String action;
    private int userID;

    public WSRequest() {
    }

    public WSRequest(String action, int userID) {
        this.action = action;
        this.userID = userID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "WSRequest{" + "action=" + action + ", userID=" + userID + '}';
    }
    
    
    
}
