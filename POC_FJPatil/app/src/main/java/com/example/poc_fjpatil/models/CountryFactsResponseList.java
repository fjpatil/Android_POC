package com.example.poc_fjpatil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class CountryFactsResponseList {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("rows")
    @Expose
    private ArrayList<CountryFactsData> data = new ArrayList<CountryFactsData>();



    /**
     *
     * @return
     * The data
     */
    public ArrayList<CountryFactsData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(ArrayList<CountryFactsData> data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}