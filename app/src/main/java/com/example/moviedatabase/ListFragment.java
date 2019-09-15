package com.example.moviedatabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment {

    List<Movie> movieList;
    listListener listListener;

    public ListFragment(List<Movie> movieList){
        this.movieList = movieList;
    }

    public interface listListener{
        public void onListSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        listListener = (listListener) getActivity();

        //1. Create recyclerView
        //2. Create the adapter and reference it to the recyclerView
        //3. Create the layoutManager and attach it to the recyclerView

        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listListener, movieList);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
