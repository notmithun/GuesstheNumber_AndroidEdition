package me.notmithun.gtn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected Button check_btn;
    protected Button regenbutton;

    protected EditText inp;

    protected int number;
    protected Random rnd_var;

    protected int wg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Game (GTN)", "Started the game...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check_btn = findViewById(R.id.chck_btn);
        regenbutton = findViewById(R.id.regen_btn);
        inp = findViewById(R.id.inpans);

        rnd_var = new Random();

        number = rnd_var.nextInt(100);

        Toast.makeText(this, "The number has been generated. You may start guessing.", Toast.LENGTH_LONG).show();

        regenbutton.setOnClickListener(v -> {
            Log.i("Game (GTN)", "A new number has been generated");
            number = rnd_var.nextInt(100);
            Toast.makeText(this, "The number has been generated. You may start guessing.", Toast.LENGTH_LONG).show();
        });
        try {
            check_btn.setOnClickListener(m -> {
                if (Integer.parseInt(inp.getText().toString()) == number) {
                    Log.v("Game (GTN)", "Player has won the game...");
                    Toast.makeText(this, "Correct answer! With a amount of " + wg + " wrong guess.", Toast.LENGTH_SHORT).show();
                } else {
                    wg += 1;
                    if (Integer.parseInt(inp.getText().toString()) > number) {
                        Toast.makeText(this, "Wrong answer! Number of wrong guess: " + wg + ". Guess a number lower than " + Integer.parseInt(inp.getText().toString()), Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(inp.getText().toString()) < number) {
                        Toast.makeText(this, "Wrong answer! Number of wrong guess: " + wg + ". Guess a number higher than " + Integer.parseInt(inp.getText().toString()), Toast.LENGTH_SHORT).show();
                    }
                    Log.v("Game (GTN)", "Player guessed the number wrong...");
                }
            });
        } catch (NumberFormatException e) {
            Log.e("Game (GTN)", "String has been entered instead of int.");
            Toast.makeText(this, "Error: Please delete 'Type a number from the input before entering the number'", Toast.LENGTH_SHORT).show();
        }
    }
}