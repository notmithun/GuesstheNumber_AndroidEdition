package me.notmithun.gtn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Handler;
import android.content.Intent;
import android.util.Log;

public class pastVersionsPage extends AppCompatActivity {

    protected Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Past Versions Page (Gtn)", "Started PVP");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        btn = findViewById(R.id.gb_btn);

        btn.setOnClickListener(v -> {
            new Handler().postDelayed(() -> {
                Intent i = new Intent(this, startActivity.class);
                startActivity(i);
                finish();
            }, 1);
        });
    }
}
