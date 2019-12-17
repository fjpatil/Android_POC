package com.example.poc_fjpatil.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.poc_fjpatil.BaseActivity;
import com.example.poc_fjpatil.R;
import com.example.poc_fjpatil.models.CountryFactsData;
import com.example.poc_fjpatil.models.CountryFactsResponseList;
import com.example.poc_fjpatil.networking.Service;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView {

    private RecyclerView list;
    SwipeRefreshLayout refreshLayout;
    @Inject
    public  Service service;
    ProgressBar progressBar;
    private String screenTitle;
    private ArrayList<? extends CountryFactsData> dataList;
    HomePresenter presenter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        if (savedInstanceState != null) {
            dataList = savedInstanceState.getParcelableArrayList("LIST");
            showTitle(savedInstanceState.getString("Title"));
        } else {
            dataList = new ArrayList<>();
            showTitle("");
        }

        renderView();
        init();

        presenter = new HomePresenter(service, this);
        presenter.getCityList();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkConnectionAndGetFacts();
            }
        });
    }

    public  void renderView(){
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.recyclerview);
        refreshLayout = findViewById(R.id.refresh_layout);
        //progressBar = findViewById(R.id.progressbar);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(CountryFactsResponseList countryFactsResponseList) {
        Log.d("test","sizee----->"+ countryFactsResponseList.getTitle());
        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), countryFactsResponseList.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CountryFactsData Item) {
                        Toast.makeText(getApplicationContext(), "Test Name",
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList("LIST", (ArrayList<? extends Parcelable>) dataList);
        outState.putString("Title", screenTitle);
    }

    @Override
    public void showTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    private void checkConnectionAndGetFacts() {
        if (isNetworkAvailable(this))
            presenter.getCityList();
        else
            Toast.makeText(this, R.string.no_network_connection, Toast.LENGTH_LONG).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
