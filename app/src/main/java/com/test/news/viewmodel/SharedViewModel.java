package com.test.news.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.news.model.NewsId;

public class SharedViewModel extends ViewModel {
    MutableLiveData<NewsId> newsIdMutableLiveData =new MutableLiveData<NewsId>();

    public void select(NewsId newsId) {
        newsIdMutableLiveData.setValue(newsId);
    }

    public LiveData<NewsId> getSelected() {
        return newsIdMutableLiveData;
    }
}
