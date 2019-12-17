package com.example.poc_fjpatil.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poc_fjpatil.R;
import com.example.poc_fjpatil.models.CountryFactsData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<CountryFactsData> data;
    private Context context;

    public HomeAdapter(Context context, List<CountryFactsData> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final HomeAdapter.ViewHolder itemViewHolder, int position) {

        final CountryFactsData model = data.get(position);
        itemViewHolder.titleTextView.setVisibility(TextUtils.isEmpty(model.getTitle()) ? View.GONE : View.VISIBLE);
        itemViewHolder.descrptionTextView.setVisibility(TextUtils.isEmpty(model.getDescription()) ? View.GONE : View.VISIBLE);
        itemViewHolder.titleTextView.setText(model.getTitle());
        itemViewHolder.descrptionTextView.setText(model.getDescription());
        itemViewHolder.imageView.setVisibility(View.VISIBLE);
        final RequestCreator picasso = Picasso.get()
                .load(model.getImageUrl()).networkPolicy(NetworkPolicy.OFFLINE);
        picasso.into(itemViewHolder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("Test", "Successfully Loaded the Image");
            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(model.getImageUrl()).into(itemViewHolder.imageView);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(CountryFactsData Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descrptionTextView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textview_title);
            descrptionTextView = itemView.findViewById(R.id.textview__description);
            imageView = itemView.findViewById(R.id.imageview);

        }


        public void click(final CountryFactsData countryFactsData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(countryFactsData);
                }
            });
        }
    }


}
