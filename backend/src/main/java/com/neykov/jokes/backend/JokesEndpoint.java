/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.neykov.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.neykov.jokesprovider.Joke;
import com.neykov.jokesprovider.JokeProvider;
import com.neykov.jokesprovider.SimpeRandomJokeProvider;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokesAPI",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokes.neykov.com",
                ownerName = "backend.jokes.neykov.com",
                packagePath = ""
        )
)
public class JokesEndpoint {

    private static JokeProvider provider = new SimpeRandomJokeProvider();


    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "getRandomJoke")
    public Joke getRandomJoke() {
        Joke joke = provider.getJoke();
        return joke;
    }

}
