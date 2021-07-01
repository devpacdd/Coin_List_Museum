package com.example.androidapi_5;

public class Coin {
    // clase moneda, para poder construir objetos.

    private String id;
    private String number;
    private String mint;
    private String image_obverse;
    private String image_reverse;
    private String date_in;
    private String date_out;
    private String material;
    private String denomination;

    private String colecction;
    private String type; // esto es un array (habrá que parsear)





    //---------------constructor---------------

    public Coin() {
    }

    public Coin(String id, String number, String mint, String image_obverse, String image_reverse, String date_in, String date_out, String material, String denomination) {
        this.id = id;
        this.number = number;
        this.mint = mint;
        this.image_obverse = image_obverse;
        this.image_reverse = image_reverse;
        this.date_in = date_in;
        this.date_out = date_out;
        this.material = material;
        this.denomination = denomination;
    }

    //-------------------------Getters setters---------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMint() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint = mint;
    }

    public String getImage_obverse() {
        return image_obverse;
    }

    public void setImage_obverse(String image_obverse) {
        this.image_obverse = image_obverse;
    }

    public String getImage_reverse() {
        return image_reverse;
    }

    public void setImage_reverse(String image_reverse) {
        this.image_reverse = image_reverse;
    }

    public String getDate_in() {
        return date_in;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    public String getDate_out() {
        return date_out;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
}
