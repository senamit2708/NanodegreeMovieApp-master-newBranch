package com.example.senamit.nanodegreemovieapp;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MovieReviewVideoLoader extends AsyncTaskLoader<List<MovieDetails>> {

    private static final String LOG_TAG = MovieReviewVideoLoader.class.getSimpleName();
    private String stringUrl;
    ArrayList<MovieDetails> movieDetailsArrayList = new ArrayList<>();
    int id=0;
    private int LOADERIDREVIEW = 36;
    private int LOADERIDVIDEO = 46;



    public MovieReviewVideoLoader(Context context) {
        super(context);
    }

    public MovieReviewVideoLoader(Context context, String stringUrl, int id) {
        super(context);
        this.stringUrl = stringUrl;
        this.id=id;
        Log.i(LOG_TAG, "inside constructor of loader");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "inside onstartloading");
        forceLoad();
    }

//    @Override
//    protected void onForceLoad() {
//        super.onForceLoad();
//        Log.i(LOG_TAG, "inside onforceload");
//    }

    @Override
    public List<MovieDetails> loadInBackground() {

        try {
            Log.i(LOG_TAG, "inside try block of review video");

            if (id==LOADERIDVIDEO){
                movieDetailsArrayList = QueryUtils.fetchMovieVideo(stringUrl);
                Log.i(LOG_TAG, "inside do in backgrougn method");
                return movieDetailsArrayList;
            }
           if (id==LOADERIDREVIEW){
                movieDetailsArrayList = QueryUtils.fetchMovieReview(stringUrl);
               return movieDetailsArrayList;
            }




        } catch (IOException e) {
            Log.i(LOG_TAG, "inside exception block 1"+e);
            e.printStackTrace();
        } catch (JSONException e) {
            Log.i(LOG_TAG, "inside exception block 2"+e);
            e.printStackTrace();
        }
        Log.i(LOG_TAG, "inside doinbackground and in null return");
        return null;

    }
}
