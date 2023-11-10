/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

/**
 *
 * @author Dac Dat
 */
public class Validate {
    public boolean checkEmail(String email) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }
    
    public boolean checkPassword(String password) {
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Za-z]).{6,20}$";
        return password.matches(PASSWORD_REGEX);
    }
    
    public boolean checkPhone(String phone) {
        String PHONE_REGEX = "^\\d{10}$";
        return phone.matches(PHONE_REGEX);
    }
}
