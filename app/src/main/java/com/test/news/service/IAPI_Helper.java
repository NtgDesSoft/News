package com.test.news.service;


import com.test.news.model.News;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAPI_Helper {

    @GET("top-headlines")
    Observable<News> getNews(@Query("country") String country,@Query("apiKey") String apiKey);

    //https://newsapi.org/v2/top-headlines?sources=reuters&apiKey=baa137ceb65c48268c8a2f5c18895d98
    //When API has a / = path
    //when API has a ? = Query
//    @GET("top-headlines")
//    Observable<News> getNewsDetails(@Query("sources") String sources,@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Observable<News> getNewsDetails2(@Query("sources") String sources, @Query("apiKey") String apiKey);



}
