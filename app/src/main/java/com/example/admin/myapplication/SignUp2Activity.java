package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SignUp2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        //get the spinner from the xml.
        Spinner dropdown = (Spinner) findViewById(R.id.confirm);
//create a list of items for the spinner.
        String[] items = new String[]{"Yes", "No", "Uncertain"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp2Activity.this, MainActivity.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("blood", getIntent().getStringExtra("blood"));
                intent.putExtra("sex",getIntent().getStringExtra("sex"));
                intent.putExtra("number", getIntent().getStringExtra("number"));

                startActivity(intent);
            }
        });

    }
    }

