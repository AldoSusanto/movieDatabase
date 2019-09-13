package com.example.moviedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textList;
    movieDbApi movieDbApi;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textList = findViewById(R.id.textList);

        //Calls the API, get and parse the JSON Object, then fill out the list of movies (var: movieList)
        getResponse();

    }

    private void writeToText() {
        if(movieList == null){
            textList.setText("We did it !");
        }
        for (Movie movie : movieList){
            String item = "";

            item += "Title: " + movie.getTitle() + "\n";
            item += "date Released: " + movie.getDateRel() + "\n";
            item += "Views: " + movie.getViews() + "\n";

            textList.append(item);
        }
    }


    public void getResponse(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieDbApi = retrofit.create(movieDbApi.class);

        Call<MovieList> call = movieDbApi.getMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d("asd", "onResponse success !");
                        MovieList temp = response.body();
                        movieList = temp.getMovieList();
                        //createListofMovies(temp);
                        //Based on the movieList variable, displays the data presented to the textView
                        writeToText();
                    }
                }else{
                    Log.d("asd", "response received but not successful");
                    textList.setText("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("asd", "onFailure is called");
                textList.setText(t.getMessage());
            }
        });

    }

}
