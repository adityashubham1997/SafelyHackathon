package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HealthActivity extends AppCompatActivity {
    private TextView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        v =(TextView) findViewById(R.id.version_number);
        final ArrayList<com.example.admin.myapplication.List> disease = new ArrayList<com.example.admin.myapplication.List>();


String name=getIntent().getStringExtra("name");
        disease.add(new List("Name", R.drawable.blood_pressure,""+name));
        disease.add(new List("Emergency contact", R.drawable.contact,getIntent().getStringExtra("number")));
        disease.add(new List("Blood Group", R.drawable.blood_group,getIntent().getStringExtra("blood")));
        disease.add(new List("Email", R.drawable.heart_rate,getIntent().getStringExtra("email")));
        disease.add(new List("BP Status", R.drawable.blood_pressure,getIntent().getStringExtra("blood")));
        disease.add(new List("Diabetes", R.drawable.diabetes_monitor,getIntent().getStringExtra("diab")));
        disease.add(new List("Cronic diseases", R.drawable.heart_rate,getIntent().getStringExtra("cronic")));

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

}