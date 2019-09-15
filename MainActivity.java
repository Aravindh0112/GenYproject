package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView headingTextView;
    TextView descTextView;
    TextView nameTextView;
    TextView classTextView;
    TextView dateTextView;
    TextView genTextView;
    TextView mailTextView;
    TextView PassTextView;
    FloatingActionButton floatingActionButton;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView= findViewById(R.id.abcd);
        classTextView = findViewById(R.id.Desc);
        dateTextView= findViewById(R.id.dob);
        genTextView=findViewById(R.id.gen);
        mailTextView=findViewById(R.id.email);
                PassTextView=findViewById(R.id.pass);
        floatingActionButton = findViewById(R.id.fabMainActivity);

        fetchData();
        fetchFirebaseData();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void fetchData() {

        {
            sharedPreferences = getSharedPreferences("DATABASE", MODE_PRIVATE);
            String headString = getSharedPreferences("DATABASE", MODE_PRIVATE).getString("HEADING", "");
            headingTextView.setText(headString);
            headingTextView.setText(sharedPreferences.getString("HEADING", ""));
            descTextView.setText(sharedPreferences.getString("DESC", ""));
        }
    }

        @Override
        protected void onResume(){
            super.onResume();
            fetchData();
            fetchFirebaseData();
        }



    public void fetchFirebaseData() {
        String uni_id = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(uni_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    headingTextView.setText(dataSnapshot.child("Heading").getValue().toString());
                    descTextView.setText(dataSnapshot.child("Desc").getValue().toString());

                } else {
                    Toast.makeText(MainActivity.this, "No data Found", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }


        });


}}



