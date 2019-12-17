package com.example.poc_fjpatil.dependency;


import com.example.poc_fjpatil.home.HomeActivity;
import com.example.poc_fjpatil.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
