package com.example.isokarhu.testreco2;

/**
 * Created by Axel on 23/12/2017.
 */

public class Images {

    private int id;
    private String image;

    public Images(){}

    public Images(String images){
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString(){
        return "ID : "+id+"\n image : "+image;
    }
}
