package com.example.quizzer.Models;

public class CategoryModel {
    private String imageURL;
    private int sets;
    private String title;

    public CategoryModel(String imageURL2, String title2, int sets2) {
        this.imageURL = imageURL2;
        this.title = title2;
        this.sets = sets2;
    }

    public CategoryModel() {
    }

    public int getSets() {
        return this.sets;
    }

    public void setSets(int sets2) {
        this.sets = sets2;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL2) {
        this.imageURL = imageURL2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }
}
