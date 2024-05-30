package com.example.foodapp.model.DTO;
import java.time.LocalDateTime;
import java.util.Date;

public class payment_history_item {
    private int id;
    private String name;
    private Date  tgiangiaohang;
    private Date tgiannhanhang;
    private Date tgianthanhtoan;
    private int price;
    private int imgSrc;
    private String hinhthucthanhtoan;


    public payment_history_item(int id, String name, Date tgiangiaohang, Date tgiannhanhang, Date tgianthanhtoan, int price, int imgSrc, String hinhthucthanhtoan) {
        this.id = id;
        this.name = name;
        this.tgiangiaohang = tgiangiaohang;
        this.tgiannhanhang = tgiannhanhang;
        this.tgianthanhtoan = tgianthanhtoan;
        this.price = price;
        this.imgSrc = imgSrc;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
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
    public Date getTgiangiaohang() {
        return tgiangiaohang;
    }

    public void setTgiangiaohang(Date tgiangiaohang) {
        this.tgiangiaohang = tgiangiaohang;
    }

    public Date getTgiannhanhang() {
        return tgiannhanhang;
    }

    public void setTgiannhanhang(Date tgiannhanhang) {
        this.tgiannhanhang = tgiannhanhang;
    }

    public Date getTgianthanhtoan() {
        return tgianthanhtoan;
    }

    public void setTgianthanhtoan(Date tgianthanhtoan) {
        this.tgianthanhtoan = tgianthanhtoan;
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

    public void setType(String type) {
        this.hinhthucthanhtoan = type;
    }

    public String getType() {
        return hinhthucthanhtoan;
    }
}

