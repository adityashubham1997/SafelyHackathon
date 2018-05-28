package com.example.admin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HealthActivity extends AppCompatActivity {
    private TextView v;
    String name;
    String email;
    String blood;
    String gender;
    String diab;
    String b_p;
    String cronic;
    String number1;
    public static  final String DEFAULT ="N/A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        v =(TextView) findViewById(R.id.version_number);
        final ArrayList<com.example.admin.myapplication.List> disease = new ArrayList<com.example.admin.myapplication.List>();


getData();
        disease.add(new List("Name", R.drawable.icon_profile_empty,name));
        disease.add(new List("Emergency contact", R.drawable.contact,number1));
        disease.add(new List("Blood Group", R.drawable.blood_group,blood));
        disease.add(new List("Email", R.drawable.heart_rate,email));
        disease.add(new List("BP Status", R.drawable.blood_pressure,b_p));
        disease.add(new List("Diabetes", R.drawable.diabetes_monitor,diab));
        disease.add(new List("Cronic diseases", R.drawable.cronic,cronic));

        final ListAdapter listAdapter = new ListAdapter(this, disease);

        ListView listView = (ListView) findViewById(R.id.listview_flavor);
        listView.setAdapter(listAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                List word = disease.get(position);
;
                }

    });
}

    private void getData()
    {

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name",DEFAULT);
        cronic = sharedPreferences.getString("chronic",DEFAULT);
        diab = sharedPreferences.getString("diab",DEFAULT);
        b_p= sharedPreferences.getString("bp",DEFAULT);
        blood = sharedPreferences.getString("blood",DEFAULT);
        email = sharedPreferences.getString("email",DEFAULT);
        gender = sharedPreferences.getString("sex",DEFAULT);
        number1 = sharedPreferences.getString("emergencycontactnumber",DEFAULT);

    }
}