package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDataActivity extends AppCompatActivity {

        EditText headingEditText;
        EditText descEditText;
        Button saveButton;
        private FirebaseDatabase firebaseDatabase;
        private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        headingEditText =findViewById(R.id.headingEditText);
        descEditText =findViewById(R.id.descEditText);
        saveButton=findViewById(R.id.buttonEditActivity);
        String uni_id = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        firebaseDatabase= FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(uni_id);

                saveButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                String headingData= headingEditText.getText().toString();
                String descData = descEditText.getText().toString();
                reference.child("Heading").setValue(headingData);
                reference.child("Desc").setValue(descData);

//                SharedPreferences sharedPreferences=
//                     getApplication().getSharedPreferences("DATABASE",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("HEADING",headingData);
//                editor.putString("DESC",descData);
//                editor.commit();

                finish();
                //SAVING THE FINISHED CODE
                Toast.makeText(AddDataActivity.this,headingData, Toast.LENGTH_SHORT).show();
            }

            });

    }
}
