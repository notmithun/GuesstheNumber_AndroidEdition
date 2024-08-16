package me.notmithun.gtn;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected Button check_btn;
    protected Button regenbutton;

    protected EditText inp;

    protected int number;
    protected Random rnd_var;

    protected int wg;

    protected StorageReference storageRef;
    private static final String TAG = "Game (GTN)";
    protected AssetManager asset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            FirebaseStorage();
        } catch (IOException e) {
            showMessage("Something went wrong", "Uh oh! Something just went wrong! Error: " + e + ". But still you can play the game! \uD83D\uDE42");
        }
        Log.v(TAG, "Started the game...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check_btn = findViewById(R.id.chck_btn);
        regenbutton = findViewById(R.id.regen_btn);
        inp = findViewById(R.id.inpans);

        rnd_var = new Random();

        number = rnd_var.nextInt(100);

        Toast.makeText(this, "The number has been generated. You may start guessing.", Toast.LENGTH_LONG).show();

        regenbutton.setOnClickListener(v -> {
            Log.i(TAG, "A new number has been generated");
            wg = 0;
            number = rnd_var.nextInt(100);
            Toast.makeText(this, "The number has been generated. You may start guessing.", Toast.LENGTH_LONG).show();
        });
        check_btn.setOnClickListener(m -> {
            try {
                if (Integer.parseInt(inp.getText().toString()) == number) {
                    Log.v(TAG, "Player has won the game...");
                    showMessage("Victory", "Correct answer! With a amount of " + wg + " wrong guess.");
                    Toast.makeText(this, "The number has been generated. You may start guessing.", Toast.LENGTH_LONG).show();
                    wg = 0;
                    number = rnd_var.nextInt(100);
                } else {
                    wg += 1;
                    if (Integer.parseInt(inp.getText().toString()) > number) {
                        showMessage("Info", "Wrong answer! Number of wrong guess: " + wg + ". Guess a number lower than " + Integer.parseInt(inp.getText().toString()));
                    } else if (Integer.parseInt(inp.getText().toString()) < number) {
                        showMessage("Info", "Wrong answer! Number of wrong guess: " + wg + ". Guess a number higher than " + Integer.parseInt(inp.getText().toString()));
                    }
                    Log.v(TAG, "Player guessed the number wrong...");
                }
            } catch (java.lang.NumberFormatException e) {
                Log.e(TAG, "String has been entered instead of int.");
                Toast.makeText(this, "Error: Please delete 'Type a number from the input before entering the number'", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    protected void FirebaseStorage() throws IOException {
        storageRef = FirebaseStorage.getInstance().getReference("uploads");
        asset = getAssets();
        try {
            InputStream is = asset.open(".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            Log.i("TR - Task Completed", "Successfully completed the user's job.");
            Toast.makeText(MainActivity.this, "Now, read it!", Toast.LENGTH_SHORT).show();
        } catch (IOException ie) {
            Log.e("TR - Caught Error", "IOException: " + ie);
            Toast.makeText(MainActivity.this, "Error has taken over, error msg: " + ie, Toast.LENGTH_SHORT).show();
        }
        InputStream stream = Files.newInputStream(new File("path/to/images/rivers.jpg").toPath());

    }
}