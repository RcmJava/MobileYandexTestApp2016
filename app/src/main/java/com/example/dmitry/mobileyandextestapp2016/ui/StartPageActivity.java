package com.example.dmitry.mobileyandextestapp2016.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.dmitry.mobileyandextestapp2016.R;
import com.example.dmitry.mobileyandextestapp2016.entities.Artist;
import com.example.dmitry.mobileyandextestapp2016.presenter.StartPagePresenter;

import java.util.List;


public class StartPageActivity extends AppCompatActivity implements StartPageView, OnClickShowDetailArtistListener {
    private RecyclerView mRecyclerView;
    private ArtistListAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    private StartPagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        initUi(savedInstanceState);
        mPresenter = new StartPagePresenter(getApplicationContext());
        mPresenter.attachView(this);
        mPresenter.loadArtists();
    }

    private void initUi(Bundle savedInstanceState) {
        mProgressDialog = new ProgressDialog(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(getString(R.string.title_start_page));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ArtistListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        mProgressDialog.setMessage(getString(R.string.message_progress_dialog));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showContent(List<Artist> list) {
        mAdapter.addContent(list);
    }

    @Override
    public void showError(int strErrorId) {
        Snackbar.make(getWindow().getDecorView(), strErrorId, Snackbar.LENGTH_LONG)
                .show();
    }

    //Callback from card item
    @Override
    public void onShowDetailArtist(int position) {
        final Intent intent = new Intent(this, DetailArtistActivity.class);
        intent.putExtra(DetailArtistActivity.ARTIST_EXTRA, mAdapter.getItem(position));

        startActivity(intent);
    }
}
