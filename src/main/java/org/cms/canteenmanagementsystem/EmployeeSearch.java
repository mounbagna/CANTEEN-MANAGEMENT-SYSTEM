package org.cms.canteenmanagementsystem;

public class EmployeeSearch {
    Integer ID;
    String Name;
    String Password;
    String Adress;
    String Email;
    Integer Telephone;

    public EmployeeSearch(Integer ID, String name, String password, String adress, String email, Integer telephone) {
        this.ID = ID;
        this.Name = name;
        this.Password = password;
        this.Adress = adress;
        this.Email = email;
        this.Telephone = telephone;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public String getAdress() {
        return Adress;
    }

    public String getEmail() {
        return Email;
    }

    public Integer getTelephone() {
        return Telephone;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setTelephone(Integer telephone) {
        Telephone = telephone;
    }
}
