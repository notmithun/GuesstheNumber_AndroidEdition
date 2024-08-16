package me.notmithun.gtn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class EmailSignUpOrLoginIn extends AppCompatActivity {

    protected String TAG = "Login";

    protected EditText password;
    protected EditText email;

    protected Button login;
    protected Button signup;

    protected String actual_password;
    protected String actual_email;

    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuporlogin_activty);
        password = findViewById(R.id.Password);
        email = findViewById(R.id.EmailAdd);
        login = findViewById(R.id.button3);
        signup = findViewById(R.id.button2);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(v -> {
            actual_password = password.getText().toString();
            actual_email = email.getText().toString();
            if (actual_password.contains(" ") || actual_email.contains(" ")) {
                Toast.makeText(this, "Please don't include any spaces in your password or email", Toast.LENGTH_SHORT).show();
            } else if (actual_password.isEmpty() || actual_email.isEmpty()) {
                Toast.makeText(this, "Your password or email is empty", Toast.LENGTH_SHORT).show();
            } else if (actual_password.length() < 8) {
                Toast.makeText(this, "Your password is lesser than 8 characters", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(actual_email, actual_password).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(EmailSignUpOrLoginIn.this, "You have been signed up! Your Firebase ID is: " + mAuth.getCurrentUser(), Toast.LENGTH_SHORT).show();
                        startActivity.setSignedIn(true);
                        switchToStartActivty();
                    } else {
                        showMessage("Something went wrong :( \n Reasons:\n1:Please check your internet connection.\n\n2:You have already signed up. Try to login.\n\nThe above reasons might be the problem.");
                        startActivity.setSignedIn(false);
                    }
                });
            }
        });

        login.setOnClickListener(v -> {
            actual_password = password.getText().toString();
            actual_email = email.getText().toString();
            mAuth.signInWithEmailAndPassword(actual_email, actual_password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "You have been logged in!", Toast.LENGTH_SHORT).show();
                    startActivity.setSignedIn(true);
                    switchToStartActivty();
                } else {
                    showMessage("Something went wrong :( \n Reasons:\n1:Please check your internet connection.\n\n2:You have never signed up but tried to login. Try to sign up.\n\nThe above reasons might be the problem.");
                    startActivity.setSignedIn(false);
                }
            });
            });

    }
    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.show();
    }
    private void switchToStartActivty() {
        new Handler().postDelayed(() -> {
            Intent i = new Intent(EmailSignUpOrLoginIn.this, startActivity.class);
            startActivity(i);
            finish();
        }, 1);
    }
}
