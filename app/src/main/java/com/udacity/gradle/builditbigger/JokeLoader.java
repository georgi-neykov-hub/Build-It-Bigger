package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.neykov.jokes.backend.jokesAPI.JokesAPI;

import java.io.IOException;

/**
 * Simple loader to fetch a random joke using the defined Google Cloud Endpoint.
 */
public class JokeLoader extends AsyncTaskLoader<LoaderResult<String>> {

    public JokeLoader(Context context) {
        super(context);
    }

    @Override
    public LoaderResult<String> loadInBackground() {
        try {
            String result = getJokesAPI().getRandomJoke().execute().getBodyText();
            return new LoaderResult<>(result);
        } catch (IOException e) {
            e.printStackTrace();
            return new LoaderResult<>(e);
        }
    }

    private JokesAPI mAPI;

    private JokesAPI getJokesAPI(){
        if(mAPI == null){
            mAPI = buildAPI();
        }
        return mAPI;
    }

    private JokesAPI buildAPI(){
        return new JokesAPI.Builder(AndroidHttp.newCompatibleTransport(),
                AndroidJsonFactory.getDefaultInstance(), null)
                .setRootUrl(BuildConfig.BACKEND_URL_HOST+"_ah/api/")
                .build();
    }
}
