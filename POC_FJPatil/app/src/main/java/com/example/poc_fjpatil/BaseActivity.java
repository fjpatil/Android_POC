package com.example.poc_fjpatil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.poc_fjpatil.dependency.DaggerDeps;
import com.example.poc_fjpatil.dependency.Deps;
import com.example.poc_fjpatil.networking.NetworkModule;

import java.io.File;

public class BaseActivity extends AppCompatActivity{
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
