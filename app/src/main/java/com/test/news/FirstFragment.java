package com.test.news;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.test.news.adapter.NewsAdapter;
import com.test.news.model.Article;
import com.test.news.model.News;
import com.test.news.model.NewsId;
import com.test.news.model.Source;
import com.test.news.utils.NetworkChecker;
import com.test.news.viewmodel.NewsListViewmodel;
import com.test.news.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Developed By Sagar Gandhi
 */

public class FirstFragment extends Fragment {
    NewsListViewmodel newsListViewmodel;
    RecyclerView recyclerView;
    NewsAdapter adapter;
    //List<Article> article;
    SharedViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //First remove the Label: name from First Fragmnet in nav_graph.xml
        getActivity().setTitle("Top News");


        recyclerView= getActivity().findViewById(R.id.recyclerview);
        //article = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        newsListViewmodel = new ViewModelProvider(requireActivity()).get(NewsListViewmodel.class);

        if(NetworkChecker.isConnected(getActivity())) {
            getObservableNewsList();
        }else{
            // Get Data from
            Snackbar snackBar = Snackbar .make(view, "An Error Occurred!", Snackbar.LENGTH_LONG) .setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getObservableNewsList();
                }
            });
            snackBar.setActionTextColor(Color.BLUE);
            View snackBarView = snackBar.getView();
            TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackBar.show();
        }



    }

    //Pass data using SharedViewModel
    public void gotoNext(final String mPos){
//        public void gotoNext(){
        NewsId newsId = new NewsId();
        newsId.setNewsId(mPos);
        model.select(newsId);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }

    public void getObservableNewsList() {
        newsListViewmodel.getNews().observe(getViewLifecycleOwner(), new Observer<News>() {
            @Override
            public void onChanged(News news) {
                Log.i("News","V"+news.getArticles().get(0).getDescription());
               // Toast.makeText(getActivity(), "" + news.getArticles().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                adapter = new NewsAdapter(news.getArticles(),getActivity(),FirstFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

}