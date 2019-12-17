package com.example.poc_fjpatil;

import com.example.poc_fjpatil.home.HomePresenter;
import com.example.poc_fjpatil.home.HomeView;
import com.example.poc_fjpatil.models.CountryFactsResponseList;
import com.example.poc_fjpatil.networking.NetworkService;
import com.example.poc_fjpatil.networking.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    private NetworkService networkService;

    @Mock
    private HomeView homeView;

    @Mock
    private CountryFactsResponseList countryFactsResponseList;

    private Service service;
    private HomePresenter homePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new Service(networkService);
        homePresenter = new HomePresenter(service, homeView);
    }

    @After
    public void teardown() {
        homePresenter.onStop();
    }

    @Test
    public void loadCountryFactsFromAPIAndLoadIntoView() {

        Observable<CountryFactsResponseList> responseObservable = Observable.just(countryFactsResponseList);
        when(networkService.getCityList()).thenReturn(responseObservable);

        homePresenter.getCityList();

        InOrder inOrder = Mockito.inOrder(homeView);
        inOrder.verify(homeView).getCityListSuccess(countryFactsResponseList);
    }
}
