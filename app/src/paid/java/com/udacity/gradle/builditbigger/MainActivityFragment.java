package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<LoaderResult<String>> {

    private static final int LOADER_ID = 1;

    private View mJokeButton;
    private View mProgressView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressView = root.findViewById(R.id.progressView);
        mJokeButton = root.findViewById(R.id.jokeButton);
        mJokeButton.setOnClickListener(this);
        return root;
    }


    @Override
    public void onDestroyView() {
        mProgressView = null;
        mJokeButton = null;
        super.onDestroyView();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == mJokeButton.getId()){
            requestJoke();
        }
    }

    private void openJokeActivity(String jokeText){
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class)
                .putExtra(JokeDisplayActivity.EXTRA_JOKE_TEXT, jokeText);
        startActivity(intent);
    }

    private void requestJoke(){
        mJokeButton.setEnabled(false);
        toggleProgressIndicator(true);
        getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();
    }

    private void toggleProgressIndicator(boolean show){
        int visibility = show? View.VISIBLE : View.INVISIBLE;
        mProgressView.setVisibility(visibility);
    }

    @Override
    public Loader<LoaderResult<String>> onCreateLoader(int id, Bundle args) {
        if(id == LOADER_ID){
            return new JokeLoader(getActivity());
        }else {
            throw new IllegalArgumentException("Unknown Loader ID.");
        }
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult<String>> loader, final LoaderResult<String> data) {
        mJokeButton.setEnabled(true);
        toggleProgressIndicator(false);
        if (data.isLoadSuccessful()) {
            final String text = data.getData();
            openJokeActivity(text);
        } else {
            showErrorMessage(R.string.message_load_failed, R.string.retry, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestJoke();
                }
            });
        }
    }

    private void showErrorMessage(@StringRes int messageResId, @StringRes int actionLabelResid, View.OnClickListener actionListener){
        Snackbar instance = Snackbar.make(getView(), messageResId, Snackbar.LENGTH_LONG);
        if(actionLabelResid != 0 && actionListener != null){
            instance.setAction(actionLabelResid,actionListener);
            int colorRes = ViewUtils.getThemeColorAccent(getActivity().getTheme());
            instance.setActionTextColor(colorRes);
        }
        instance.show();
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult<String>> loader) {
    }
}
