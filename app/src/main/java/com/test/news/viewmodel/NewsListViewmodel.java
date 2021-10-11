package com.test.news.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.news.model.Article;
import com.test.news.model.News;
import com.test.news.model.Source;
import com.test.news.repository.NewsRepository;

public class NewsListViewmodel extends AndroidViewModel {
    NewsRepository newsRepository;
    public NewsListViewmodel(@NonNull Application application) {
        super(application);
        newsRepository = NewsRepository.getInstance();
    }

    public LiveData<News> getNews() {
        return newsRepository.getNewsList();
    }


    public LiveData<News> getNewsDetails2(String position){
        return newsRepository.getNewsDetails2(position);
    }

//    public LiveData<News> getNewsDetails() {
//        return newsRepository.getNewsDetails();
//    }
}
