package com.example.user.myapplication.topsongs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.api.ApiService;
import com.example.user.myapplication.api.Tracks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongDetailsActivity extends AppCompatActivity {

    public static final String TRACK = "track";
    public static final String ARTIST = "artist";
    public static final String TRACK_ID = "track_id";

    String track;
    String artist;
    int trackid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        track = getIntent().getStringExtra(TRACK);
        artist = getIntent().getStringExtra(ARTIST);
        trackid = getIntent().getIntExtra(TRACK_ID, -1);

        getSupportActionBar().setTitle(track);
        getSupportActionBar().setSubtitle(artist);

        ApiService.getService().getTrack(trackid).enqueue(new Callback<Tracks>() {
            @Override
            public void onResponse(@NonNull Call<Tracks> call, @NonNull Response<Tracks>
                    response) {
                Toast.makeText(
                        SongDetailsActivity.this,
                        "Pobrano dane", Toast.LENGTH_SHORT
                ).show();
            }

            @Override
            public void onFailure(@NonNull Call<Tracks> call, @NonNull Throwable t) {
                Toast.makeText(
                        SongDetailsActivity.this,
                        "Błąd pobierania danych: " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();

        return true;
    }


}
