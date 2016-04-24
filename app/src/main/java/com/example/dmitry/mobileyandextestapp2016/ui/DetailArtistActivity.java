package com.example.dmitry.mobileyandextestapp2016.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dmitry.mobileyandextestapp2016.R;
import com.example.dmitry.mobileyandextestapp2016.entities.Artist;


public class DetailArtistActivity extends AppCompatActivity implements View.OnClickListener {
    static final String ARTIST_EXTRA = "detailArtist";

    private ImageView mImage;
    private TextView mDescriptionLabel;
    private View mLinkView;

    private Artist mArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArtist = getIntent().getParcelableExtra(ARTIST_EXTRA);

        setContentView(R.layout.activity_details);
        initUi(savedInstanceState);
    }

    private void initUi(Bundle savedInstanceState) {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mArtist.name);
        }

        mImage = (ImageView) findViewById(R.id.image);
        Glide.with(mImage.getContext().getApplicationContext())
                .load(mArtist.cover.big)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(mImage);
        mImage.setOnClickListener(this);

        mDescriptionLabel = (TextView) findViewById(R.id.description);
        mDescriptionLabel.setText(mArtist.description);

        mLinkView = findViewById(R.id.link_view);
        TextView linkLabel = (TextView) findViewById(R.id.linkLabel);

        if (TextUtils.isEmpty(mArtist.link)) {
            mLinkView.setVisibility(View.GONE);
            linkLabel.setVisibility(View.GONE);

        } else {
            mLinkView.setOnClickListener(this);

            TextView link = (TextView) mLinkView.findViewById(R.id.link);
            link.setText(mArtist.link);
        }

        TextView counts = (TextView) findViewById(R.id.tracks);
        counts.setText(getString(R.string.text_artist_card, mArtist.albums, mArtist.tracks));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image) {
            final Intent intent = new Intent(this, FullScreenImageActivity.class);
            intent.putExtra(FullScreenImageActivity.COVER_EXTRA, mArtist.cover.big);

            startActivity(intent);
        } else if (v.getId() == R.id.link_view) {
            final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mArtist.link));
            startActivity(browserIntent);
        }
    }
}
