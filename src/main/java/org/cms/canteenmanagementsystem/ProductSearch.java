package org.cms.canteenmanagementsystem;

public class ProductSearch {
    Integer ID;
    String Name;
    Integer Quantity;
    Double Price;

    public ProductSearch(Integer ID, String name, Integer quantity, Double price) {
        this.ID = ID;
        this.Name = name;
        this.Quantity = quantity;
        this.Price = price;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setQuantity(Integer quantity) {
        this.Quantity = quantity;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }
}
