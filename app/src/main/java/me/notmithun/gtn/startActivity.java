package me.notmithun.gtn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Handler;
import android.content.Intent;
import android.util.Log;

public class startActivity extends AppCompatActivity {

    protected Button btn;
    protected Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Guess the Number (Start Screen) (V1)", "The app has started. Version 1 (V1)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        btn = findViewById(R.id.nxt_scrn_btn);
        btn2 = findViewById(R.id.button);
        btn.setOnClickListener(v -> new Handler().postDelayed(() -> {
            Intent i = new Intent(startActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 1));
        btn2.setOnClickListener(m -> new Handler().postDelayed(() -> {
            Intent i = new Intent(startActivity.this, pastVersionsPage.class);
            startActivity(i);
            finish();
        }, 1));
    }
}
