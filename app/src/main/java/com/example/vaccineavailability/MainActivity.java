package com.example.vaccineavailability;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button;
    DatePickerDialog datePickerDialog;
    EditText editText;
    String pinCode;
    String date;
    CheckBox check_18,check_45;
    ArrayList<Integer> ages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setText(getTodayDate());
        check_18=findViewById(R.id.checkBox2);
        check_45=findViewById(R.id.checkBox3);
        ages=new ArrayList<>();

        check_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_18.isChecked()){
                    ages.add(18);
                }else{
                    ages.remove(Integer.valueOf(18));
                }
            }
        });

        check_45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_45.isChecked()){
                    ages.add(45);
                }else{
                    ages.remove(Integer.valueOf(45));
                }
            }
        });

        initDatePicker();
    }

    private String getTodayDate() {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DATE);
        month++;
        return (date+"-"+month+"-"+year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                button.setText(day+"-"+month+"-"+year);
            }
        };

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DATE);
        int style= AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,date);
    }

    public void data_pick(View view){
        datePickerDialog.show();
    }

    public void find(View view) {
        String URL1="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=";

        editText=findViewById(R.id.editTextNumberDecimal);
        pinCode=editText.getText().toString();
        pinCode.trim();

        date=button.getText().toString();

        if(TextUtils.isEmpty(pinCode) || pinCode.length()<6){
            Toast.makeText(this,"Enter valid Pin code",Toast.LENGTH_SHORT).show();
        }else {
            String URL = URL1 + pinCode + "&date=" + date;
            Intent intent=new Intent(this,SlotsPage.class);
            intent.putExtra("URL",URL);
            intent.putIntegerArrayListExtra("Ages",ages);
            startActivity(intent);
        }
    }
}