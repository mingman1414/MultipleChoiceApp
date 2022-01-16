package com.example.appdoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DiemCaoActivity extends AppCompatActivity {

    ListView lstDiem;
    ArrayList<Diem> arrayDiem;
    DiemCaoAdapter adapter;
    String urlGetData = "http://192.168.1.14:8080/DoAnAndroid/getdiemcao.php?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_cao);

        lstDiem = (ListView) findViewById(R.id.listDiem);
        arrayDiem = new ArrayList<>();
        adapter = new DiemCaoAdapter(this, R.layout.dong_diemcao, arrayDiem);
        lstDiem.setAdapter(adapter);

        //nhận tên môn học từ main
        Intent intent = getIntent();
        int monhocID = intent.getIntExtra("id", 0);
        String tenMonHoc = intent.getStringExtra("tenmonhoc");

        GetData(urlGetData + monhocID);
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(CauHoiActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                arrayCauHoi = new ArrayList<>();
                for(int i=0; i<response.length(); i++){
                    try{
                        JSONObject object = response.getJSONObject(i);
                        arrayDiem.add(new Diem(
                                i+1,
                                object.getString("ten"),
                                object.getInt("diem")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DiemCaoActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}