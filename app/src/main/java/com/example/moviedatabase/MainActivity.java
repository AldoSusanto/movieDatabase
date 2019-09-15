package com.example.moviedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListFragment.listListener {

    private Retrofit retrofit;

    private movieDbApi movieDbApi;
    private List<Movie> popularMovieList;
    private List<Movie> topRatedMovieList;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);


        //Calls the API, get and parse the JSON Object, then fill out the list of movies (var: movieList)
        getResponse();

    }


    public void getResponse(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieDbApi = retrofit.create(movieDbApi.class);

        Call<MovieList> call = movieDbApi.getPopularList();


        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d("asd", "onResponse success !");
                        MovieList temp = response.body();
                        popularMovieList = temp.getMovieList();

                        //Based on the movieList variable, displays the data presented to the textView
                        //activateFragment();

                        getResponseTopRated();

                    }
                }else{
                    Log.d("asd", "Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("asd", "onFailure is called");
                Log.d("asd", t.getMessage());
            }
        });

    }

    private void getResponseTopRated() {
        Call<MovieList> call = movieDbApi.getTopRatedList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        MovieList temp = response.body();
                        topRatedMovieList = temp.getMovieList();
                        activateViewPager();

                    }
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("asd", "onFailure on TopRated is called");
                Log.d("asd", t.getMessage());
            }
        });
    }

    private void activateViewPager(){
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), popularMovieList, topRatedMovieList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

//    //Sets the movie List fragment to the mainActivity
//    private void activateFragment() {
//        // Start activating the fragment
//        //1. Create listFragment class
//        //2. Create FragmentManager
//        //3. Create FragmentTransaction based on FragmentManager
//        //4. Using the fragmentTransaction object, we connect the id of layout
//        //   with the listFragment object that we have
//        //5. Commit the transaction
//
//        //0.5 Check whether a fragment already existed or not. If already exist, then we don't create another fragment
//        ListFragment savedFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.placeHolder);
//        if(savedFragment == null){
//            // Start activating the fragment
//            ListFragment listFragment = new ListFragment(movieList);
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//            fragmentTransaction.add(R.id.placeHolder, listFragment);
//            fragmentTransaction.commit();
//            Log.d("asd", "List Fragment displayed");
//        }
//
//    }

    @Override
    public void onListSelected(int index) {

    }
}
