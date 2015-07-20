package com.neykov.jokesprovider;

public class PictureJoke extends Joke {

    private String imageUrl;

    public PictureJoke(String bodyText, String imageUrl) {
        super(bodyText);
        this.imageUrl = imageUrl;
    }
}
