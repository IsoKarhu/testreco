package com.example.isokarhu.testreco2;

/**
 * Created by Axel on 20/12/2017.
 */

public class Mots {

    private int id;
    private String word;
    private String flag;
    private String image;

    public Mots(){}

    public Mots(String word, String flag, String image){
        this.word = word;
        this.flag = flag;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString(){
        return "ID : "+id+"\n word : "+word+"\n flag : "+flag +"\n image : "+image;
    }
}
