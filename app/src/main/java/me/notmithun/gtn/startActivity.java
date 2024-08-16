package me.notmithun.gtn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.os.Handler;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class startActivity extends AppCompatActivity {

    protected Button btn;
    protected static final String TAG = "startPage";
    protected static boolean isSignedIn = false;
    protected Button signInBtn;
    protected FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "The app has started. Version 1 (V1)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        mAuth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.nxt_scrn_btn);
        signInBtn = findViewById(R.id.SignInBtn);
        btn.setOnClickListener(v -> {
            if (isSignedIn) {
                new Handler().postDelayed(() -> {
                    Intent i = new Intent(startActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }, 1);
            } else {
                Toast.makeText(this, "Please login before playing...", Toast.LENGTH_LONG).show();
            }
        });
        signInBtn.setOnClickListener(v -> new Handler().postDelayed(() -> {
            Intent i = new Intent(startActivity.this, EmailSignUpOrLoginIn.class);
            startActivity(i);
            finish();
        }, 1));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar_for_start_screen, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit) {
            finish();
            System.exit(0);
            return true;
        } else if (id == R.id.strt_gme) {
            new Handler().postDelayed(() -> {
                Intent i = new Intent(startActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }, 1);
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public static void setSignedIn(boolean yon) {
        isSignedIn = yon;
    }
}