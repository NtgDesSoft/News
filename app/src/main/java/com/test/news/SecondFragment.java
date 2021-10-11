package com.test.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.news.adapter.NewsDetailsAdapter;
import com.test.news.model.Article;
import com.test.news.model.News;
import com.test.news.model.NewsId;
import com.test.news.model.Source;
import com.test.news.utils.RecyclerTouchListener;
import com.test.news.viewmodel.NewsListViewmodel;
import com.test.news.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Developed By Sagar Gandhi
 */

public class SecondFragment extends Fragment {
    NewsListViewmodel newsListViewmodel;
    SharedViewModel model;
    RecyclerView recyclerView;
    List<Article> articleList;
    NewsDetailsAdapter newsDetailsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= getActivity().findViewById(R.id.recyclerview2);
        articleList = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        newsListViewmodel = new ViewModelProvider(requireActivity()).get(NewsListViewmodel.class);

        model= ViewModelProviders.of(this).get(SharedViewModel.class);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //Get data using SharedViewModel
        model.getSelected().observe(getViewLifecycleOwner(), new Observer<NewsId>() {
            @Override
            public void onChanged(NewsId newsId) {
                Log.i("Valuess",""+newsId.getNewsId());
                observablesDetails(newsId.getNewsId());

                //To display news id on Fragment Action bar
                getActivity().setTitle(newsId.getNewsId());
            }
        });

   //     getObservableNewsDetails();

    }

    /**
     * This method will get the id of clickable recycler view item and pass as a id to viewModel
     * @param position
     */
        public void observablesDetails(final String position){
            newsListViewmodel= ViewModelProviders.of(this).get(NewsListViewmodel.class);
            newsListViewmodel.getNewsDetails2(position).observe(getViewLifecycleOwner(), new Observer<News>() {
                @Override
                public void onChanged(News news) {
                    newsDetailsAdapter = new NewsDetailsAdapter(news.getArticles(),getActivity());
                    recyclerView.setAdapter(newsDetailsAdapter);
                }
            });


//    public void getObservableNewsDetails() {
//        newsListViewmodel.getNewsDetails().observe(getViewLifecycleOwner(), new Observer<News>() {
//            @Override
//            public void onChanged(News news) {
//                Log.i("NewsD","V"+news.getArticles().get(0).getContent());
//                newsDetailsAdapter = new NewsDetailsAdapter(news.getArticles(),getActivity());
//
//                recyclerView.setAdapter(newsDetailsAdapter);
//            }
//        });

    }
}