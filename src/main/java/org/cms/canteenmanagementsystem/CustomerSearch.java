package org.cms.canteenmanagementsystem;

public class CustomerSearch {
    Integer ID;
    String Name;
    String Password;
    String Adress;
    String Email;
    Integer Telephone;

    public CustomerSearch(Integer ID, String name, String Password,String Adress,String Email,Integer Telephone) {
        this.ID = ID;
        this.Name = name;
        this.Password = Password;
        this.Adress = Adress;
        this.Email = Email;
        this.Telephone = Telephone;
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
        this.Name = name;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    public void setAdress(String Adress) {
        this.Adress = Adress;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void setTelephone(Integer Telephone) {
        this.Telephone = Telephone;
    }
}
