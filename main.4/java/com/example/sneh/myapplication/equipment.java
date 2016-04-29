package com.example.sneh.myapplication;

/**
 * Created by starsilver on 5/11/15.
 */
public class equipment {

    private int equip_id,cicle_id,building_id;
    private String type,designation;
    private int quantity,price;

    public int getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public int getCicle_id() {
        return cicle_id;
    }

    public void setCicle_id(int cicle_id) {
        this.cicle_id = cicle_id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
