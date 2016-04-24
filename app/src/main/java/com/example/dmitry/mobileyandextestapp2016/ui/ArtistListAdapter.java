package com.example.dmitry.mobileyandextestapp2016.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitry.mobileyandextestapp2016.R;
import com.example.dmitry.mobileyandextestapp2016.entities.Artist;

import java.util.ArrayList;
import java.util.List;


public class ArtistListAdapter extends RecyclerView.Adapter<ArtistViewHolder> {
    private List<Artist> mArtistList = new ArrayList<>();

    private OnClickShowDetailArtistListener mClickListener;

    public ArtistListAdapter(final OnClickShowDetailArtistListener onClickShowDetailArtistListener) {
        mClickListener = onClickShowDetailArtistListener;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_artist, parent, false);

        return new ArtistViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        if (!mArtistList.isEmpty()) {
            holder.bind(mArtistList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mArtistList.size();
    }

    public Artist getItem(int position) {
        return mArtistList.get(position);
    }

    public void addContent(List<Artist> list) {
        mArtistList.addAll(list);
        notifyItemRangeInserted(0, mArtistList.size());
    }
}
