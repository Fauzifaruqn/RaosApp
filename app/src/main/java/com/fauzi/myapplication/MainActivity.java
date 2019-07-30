package com.fauzi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnSigIn, btnSignUp ;

    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSigIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        txtSlogan = (TextView) findViewById(R.id.txtSlogan);

        Typeface face = Typeface.createFromAsset(getAssets(),"font/Nabila.ttf");
        txtSlogan.setTypeface(face);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });

        btnSigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signIn = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);

            }
        });

    }


    // Write a message to the database

}
