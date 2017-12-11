package com.android.pharmeasy.pharmeasydemoproject.ui.pharmeasylist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pharmeasy.pharmeasydemoproject.data.model.Data;
import com.android.pharmeasy.pharmeasydemoproject.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 12/10/2017.
 */

class PharmEasyListAdapter extends RecyclerView.Adapter<PharmEasyListAdapter.ViewHolder> {

    private List<Data> pharmEasyData;
    private Fragment fragment;

    public PharmEasyListAdapter(Fragment fragment, List<Data> pharmEasyData) {
        this.fragment = fragment;
        this.pharmEasyData = pharmEasyData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_first_name)
        TextView tvFirstName;

        @BindView(R.id.tv_last_name)
        TextView tvLastName;

        @BindView(R.id.iv_list_person_img)
        ImageView ivPersonPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public PharmEasyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_pharmeasy_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PharmEasyListAdapter.ViewHolder viewHolder, int position) {

        Data data = pharmEasyData.get(position);

        String photoUrl = data.getAvatar();

        viewHolder.tvFirstName.setText(data.getFirst_name());
        viewHolder.tvLastName.setText(data.getLast_name());

        Glide.with(fragment).load(photoUrl).placeholder(R.drawable.ic_photo_place_holder).error(
                R.drawable.ic_photo_place_holder).into(viewHolder.ivPersonPhoto);
    }

    @Override
    public int getItemCount() {
        return pharmEasyData.size();
    }

    public void clear() {
        int size = getItemCount();
        pharmEasyData.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<Data> pharmEasyData) {
        int prevSize = getItemCount();
        this.pharmEasyData.addAll(pharmEasyData);
        notifyItemRangeInserted(prevSize, pharmEasyData.size());
    }
}
