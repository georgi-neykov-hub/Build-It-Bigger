package com.neykov.jokesprovider;

import java.io.Serializable;

public abstract class Joke implements Serializable {

    private final String bodyText;

    protected Joke(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getBodyText() {
        return bodyText;
    }
}
