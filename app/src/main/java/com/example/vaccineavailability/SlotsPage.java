package com.example.vaccineavailability;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SlotsPage extends AppCompatActivity {

    SlotsAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Slots> sessionsList_18;
    ArrayList<Slots> sessionsList_45;
    ArrayList<Slots> sessionsList;
    ProgressBar progressBar;
    ArrayList<Integer> ages;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        Intent intent=getIntent();
        String URL=intent.getStringExtra("URL");
        ages=intent.getIntegerArrayListExtra("Ages");

        recyclerView=findViewById(R.id.recycle);
        progressBar=findViewById(R.id.progress);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sessionsList_18=new ArrayList<Slots>();
        sessionsList_45=new ArrayList<Slots>();
        sessionsList=new ArrayList<Slots>();
        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray sessions=response.getJSONArray("sessions");
                    for(int i=0;i<sessions.length();i++){
                        JSONObject jsonObject=sessions.getJSONObject(i);
                        Slots news=new Slots(
                                jsonObject.getString("name"),
                                jsonObject.getString("address"),
                                jsonObject.getString("vaccine"),
                                jsonObject.getString("available_capacity"),
                                jsonObject.getString("available_capacity_dose1"),
                                jsonObject.getString("available_capacity_dose2"),
                                jsonObject.getString("min_age_limit"),
                                jsonObject.getString("fee")
                        );

                        sessionsList.add(news);

                        if(Integer.parseInt(news.min_age_limit)== 18){
                            sessionsList_18.add(news);
                        }else{
                            sessionsList_45.add(news);
                        }
                    }
                    progressBar.setVisibility(View.GONE);

                    if(ages.size()==0 ||ages.size()==2){
                        adapter=new SlotsAdapter(sessionsList);
                    }else{
                        if(ages.get(0)==18){
                            adapter=new SlotsAdapter(sessionsList_18);
                        }else{
                            adapter=new SlotsAdapter(sessionsList_45);
                        }
                    }

                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SlotsPage.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
