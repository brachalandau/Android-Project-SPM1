package com.example.spm1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.spm1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("yay", "Value is: " + value);

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Oops something went wrong,hello world was not added", Toast.LENGTH_SHORT).show();

                Log.w("bhxh", "Failed to read value.", error.toException());
            }
        });


    }

    public void goToAddParcelActivity(View v)
    {
        Intent AddParcelIntent=new Intent(MainActivity.this,AddParcelActivity.class);
        startActivity(AddParcelIntent);
    }
    public void goToHistoryParcelActivity(View v)
    {
        Intent HistoryParcelIntent=new Intent(MainActivity.this,HistoryParcelsActivity.class);
        startActivity(HistoryParcelIntent);
    }
}
