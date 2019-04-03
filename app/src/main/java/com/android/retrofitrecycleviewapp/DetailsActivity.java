package com.android.retrofitrecycleviewapp;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DetailsActivity extends AppCompatActivity {

    public static final String URL="url";
    public static final String TM_URL="thumbnail_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final ProgressBar progressBar=findViewById(R.id.circle_progress);
        ImageView imageView=findViewById(R.id.detail_image);
        String url=getIntent().getStringExtra(URL);
        String tmUrl=getIntent().getStringExtra(TM_URL);

        Glide.with(this).load(url)
                .thumbnail(Glide.with(this).load(tmUrl))
                .transition(withCrossFade(500))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(DetailsActivity.this,"Error,Try again later",Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.INVISIBLE);
                        return false;
                    }
                })
                .into(imageView);
    }
}
