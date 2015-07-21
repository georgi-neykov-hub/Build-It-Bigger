package com.neykov.jokesprovider;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpeRandomJokeProvider implements JokeProvider {

    private static List<Joke> buildJokeList(){
        Joke[] jokes = {
                new TextJoke("Q: What do you get when you cross an octopus with a cow?\n" +
                        "A: An animal that can milk itself."),
                new TextJoke("What did one computer say to the other?\n" +
                        "010101101010101010101"),
                new TextJoke("After Bill Gates wedding night, his wife finally knew why he called his company Microsoft."),
                new TextJoke("Q: How do you stop a lawyer from drowning?\n" +
                        "\n" +
                        "A: Shoot him before he hits the water.")
        };
        return Arrays.asList(jokes);
    }

    private final List<Joke> jokes;
    private Random random;

    public SimpeRandomJokeProvider() {
        this.jokes = new CopyOnWriteArrayList<>(buildJokeList());
        this.random = new Random();
    }

    @Override
    public Joke getJoke() {
        List<Joke> currentJokes = this.jokes;
        int min = 0, max = jokes.size() - 1;
        int index = random.nextInt((max - min) + 1) + min;
        return currentJokes.get(index);
    }
}
