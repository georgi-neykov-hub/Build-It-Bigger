package com.neykov.jokesprovider;

public abstract class Joke {
    private final String bodyText;

    protected Joke(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getBodyText() {
        return bodyText;
    }
}
