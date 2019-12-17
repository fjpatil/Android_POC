package com.example.poc_fjpatil.home;

import com.example.poc_fjpatil.models.CountryFactsResponseList;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(CountryFactsResponseList countryFactsResponseList);

    void showTitle(String title);


}
