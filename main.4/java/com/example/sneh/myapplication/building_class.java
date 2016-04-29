package com.example.sneh.myapplication;

/**
 * Created by starsilver on 4/11/15.
 */
public class building_class {
    private int building_id,cicle_id;
    private String title;
    private int capacity;
    private String other,type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
