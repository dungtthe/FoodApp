package com.example.foodapp.model.DTO;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private int price;
    private int imgSrc;
    private String type;


    public Product(int id, String name, int quantity, int price, int imgSrc,String type) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imgSrc = imgSrc;
        this.type=type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setType(String type){
        this.type=type;
    }
    public String getType(){
        return type;
    }


}
