package com.test.news.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.test.news.model.Article;
import com.test.news.model.Constants;
import com.test.news.model.News;
import com.test.news.model.Source;
import com.test.news.service.IAPI_Helper;
import com.test.news.service.ServiceHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository {
    public static NewsRepository newsRepository;
    static IAPI_Helper iRestHelper;
    static CompositeDisposable mDisposable;


    public synchronized static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
            iRestHelper = ServiceHelper.getRestAPIHelper();
            mDisposable = new CompositeDisposable();
        }
        return newsRepository;
    }

    public static MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<News> getNewsList() {
//        IAPI_Helper iRestHelper = ServiceHelper.getRestAPIHelper();
        final MutableLiveData<News> newsResponseLiveData = new MutableLiveData<>();
        //mDisposable = new CompositeDisposable();

        mDisposable.add(iRestHelper.getNews(Constants.COUNTRY,Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        newsResponseLiveData.setValue(news);
                        Log.i("News",news.getArticles().get(0).getTitle());
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
//                        ToastUtils.showError(NetworkChecker.getErrorMessage(e), MyApplication.getAppContext());
//                        isLoading.setValue(false);

                    }
                }));

        return newsResponseLiveData;
    }


    //TODO:
    public MutableLiveData<News> getNewsDetails2(String position){
        final MutableLiveData<News> newsMutableLiveData2 = new MutableLiveData<>();
        IAPI_Helper iRestHelper = ServiceHelper.getRestAPIHelper();
        iRestHelper.getNewsDetails2(position, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(News news) {
                        newsMutableLiveData2.setValue(news);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return newsMutableLiveData2;
    }


//    public MutableLiveData<News> getNewsDetails() {
//        final MutableLiveData<News> newsDetailMutableLiveData = new MutableLiveData<>();
//        mDisposable.add(
//                iRestHelper.getNewsDetails(Constants.SOURCE,Constants.API_KEY)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<News>() {
//                            @Override
//                            public void accept(News news) throws Exception {
//                                newsDetailMutableLiveData.setValue(news);
//                                Log.i("HTT_NewsDetails",news.getArticles().get(0).getTitle());
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//
//                            }
//
//                        }));
//        return newsDetailMutableLiveData;
//    }

}