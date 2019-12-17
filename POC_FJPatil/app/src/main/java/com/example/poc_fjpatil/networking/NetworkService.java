package com.example.poc_fjpatil.networking;


import com.example.poc_fjpatil.models.CountryFactsResponseList;

import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<CountryFactsResponseList> getCityList();

}
