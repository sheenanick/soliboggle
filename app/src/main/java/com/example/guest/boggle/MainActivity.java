package com.example.guest.boggle;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mainTitleTextView) TextView mMainTitleTextView;
    @Bind(R.id.playButton) Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface komikaxFont = Typeface.createFromAsset(getAssets(), "fonts/komikax.ttf");
        mMainTitleTextView.setTypeface(komikaxFont);

        mPlayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPlayButton) {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        }
    }
}
