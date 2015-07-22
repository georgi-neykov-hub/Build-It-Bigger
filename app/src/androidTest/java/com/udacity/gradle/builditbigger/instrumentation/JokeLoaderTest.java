package com.udacity.gradle.builditbigger.instrumentation;

import android.test.SupportLoaderTestCase;

import com.udacity.gradle.builditbigger.JokeLoader;
import com.udacity.gradle.builditbigger.LoaderResult;

import junit.framework.Assert;


public class JokeLoaderTest extends SupportLoaderTestCase{

    private JokeLoader loader;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loader = new JokeLoader(getContext());
    }

    public void testLoaderLoadsResult(){
        LoaderResult<String> result = getLoaderResultSynchronously(loader);
        Assert.assertNotNull("Null LoaderResult returned", result);
        if(result.isLoadSuccessful()){
            Assert.assertTrue("Load is successful, but no data was returned.", result.getData()!= null);
        } else {
            Assert.assertTrue("Load isn't successful, but no error was returned.", result.getError() != null);
        }
    }
}