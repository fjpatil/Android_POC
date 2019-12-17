package com.example.poc_fjpatil.home;

import com.example.poc_fjpatil.models.CountryFactsResponseList;
import com.example.poc_fjpatil.networking.NetworkError;
import com.example.poc_fjpatil.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {

        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CountryFactsResponseList cityListResponse) {
                view.getCityListSuccess(cityListResponse);
                view.showTitle(cityListResponse.getTitle());
            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
