package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp2Activity extends AppCompatActivity {
    EditText chronic;
    Spinner dropdown;
    Spinner dropdown1;
    String cronic;
    String b_p;
    String diab;
    String DEFAULT = "N/A";
    String name1;
    String email1;
    String bloodgroup1;
    String sex1;
    String contactNumber1;
    String number1;
//    SignUp2Activity signUp2Activity = new SignUp2Activity();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mMessageDatabaseReference = mFirebaseDatabase.getReference().child("Safely_User_Data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up2);
        //Diab = (Spinner) findViewById(R.id.spinner1);

        //get the spinner from the xml.
        dropdown = (Spinner) findViewById(R.id.confirm);
        chronic = (EditText)findViewById(R.id.chronic);
//create a list of items for the spinner.
        String[] items = new String[]{"Yes", "No"};
        String[] items1 = new String[]{"High","Low","Normal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown1 = (Spinner) findViewById(R.id.b_p);
        dropdown1.setAdapter(adapter1);



       Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp2Activity.this, MainActivity.class);
                //intent.putExtra("name", getIntent().getStringExtra("name"));
                //intent.putExtra("email", getIntent().getStringExtra("email"));
                //intent.putExtra("blood", getIntent().getStringExtra("blood"));
                //intent.putExtra("sex",getIntent().getStringExtra("sex"));
                //intent.putExtra("number", getIntent().getStringExtra("number"));
                setData1();
                getData1();
                startActivity(intent);
                // TODO: Send data to database.
                DatabaseStuff datainput1 = new DatabaseStuff(name1, email1, sex1, bloodgroup1, number1, contactNumber1,diab,b_p,cronic);
                mMessageDatabaseReference.push().setValue(datainput1);

            }
        });

    }
    private void setData1()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("chronic",chronic.getText().toString());
        editor.putString("diab", dropdown.getSelectedItem().toString());
        editor.putString("bp", dropdown1.getSelectedItem().toString());

        editor.commit();

        Toast.makeText(this,"Medical Data was saved successfully",Toast.LENGTH_LONG).show();

    }
    private void getData1()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        cronic = sharedPreferences.getString("chronic",DEFAULT);
        diab = sharedPreferences.getString("diab",DEFAULT);
        b_p= sharedPreferences.getString("bp",DEFAULT);
        name1 = sharedPreferences.getString("name",DEFAULT);
        email1 = sharedPreferences.getString("email",DEFAULT);
        sex1 = sharedPreferences.getString("sex",DEFAULT);
        bloodgroup1 = sharedPreferences.getString("blood",DEFAULT);
        contactNumber1 = sharedPreferences.getString("contactnumber",DEFAULT);
        number1 = sharedPreferences.getString("emergencycontactnumber",DEFAULT);
    }

}

