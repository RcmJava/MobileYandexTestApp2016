package com.example.dmitry.mobileyandextestapp2016.ui;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dmitry.mobileyandextestapp2016.R;
import com.example.dmitry.mobileyandextestapp2016.entities.Artist;


public class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ImageView mImage;
    private final TextView mTitleLabel;
    private final TextView mGenresLabel;
    private final TextView mCountsLabel;

    private OnClickShowDetailArtistListener mClickListener;

    public ArtistViewHolder(View itemView, OnClickShowDetailArtistListener onClickShowDetailArtistListener) {
        super(itemView);
        itemView.setOnClickListener(this);

        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitleLabel = (TextView) itemView.findViewById(R.id.title);
        mGenresLabel = (TextView) itemView.findViewById(R.id.genres);
        mCountsLabel = (TextView) itemView.findViewById(R.id.counts);

        mClickListener = onClickShowDetailArtistListener;
    }

    public void bind(Artist artist) {
        Resources resources = itemView.getResources();

        mTitleLabel.setText(artist.name);
        mGenresLabel.setText( TextUtils.join(", ", artist.genres));
        mCountsLabel.setText(resources.getString(R.string.text_artist_card, artist.albums, artist.tracks));

        Glide.with(mImage.getContext().getApplicationContext())
                .load(artist.cover.small)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(mImage);
    }

    @Override
    public void onClick(View v) {
        mClickListener.onShowDetailArtist(getAdapterPosition());
    }
}
