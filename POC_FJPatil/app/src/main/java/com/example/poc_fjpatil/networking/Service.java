package com.example.poc_fjpatil.networking;


import com.example.poc_fjpatil.models.CountryFactsResponseList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCityListCallback callback) {

        return networkService.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CountryFactsResponseList>>() {
                    @Override
                    public Observable<? extends CountryFactsResponseList> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CountryFactsResponseList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CountryFactsResponseList countryFactsResponseList) {
                        callback.onSuccess(countryFactsResponseList);

                    }
                });
    }

    public interface GetCityListCallback{
        void onSuccess(CountryFactsResponseList countryFactsResponseList);

        void onError(NetworkError networkError);
    }
}
