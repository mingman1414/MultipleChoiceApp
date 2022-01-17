package com.example.appdoan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ThemDiemActivity extends AppCompatActivity {

    private TextView txtIdMon;
    private EditText edtTen;
    private EditText edtDiem;
    private EditText edtMonHoc;
    private Button btnNhap;
    private Button btnHuy;
    String urlThem = "http://192.168.1.14:8080/DoAnAndroid/insertdiem.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Dialog);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_diem);
        AnhXa();

        Intent intent = getIntent();
        String diem = intent.getStringExtra("diem");
        String tenMonHoc = intent.getStringExtra("tenmonhoc");
        String idmonhoc = intent.getStringExtra("idmonhoc");

        edtDiem.setText(diem);
        edtMonHoc.setText(tenMonHoc);
        txtIdMon.setText(idmonhoc);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTen.getText().toString().trim();
                if(ten.isEmpty()){
                    Toast.makeText(ThemDiemActivity.this, "Hãy nhập tên", Toast.LENGTH_SHORT).show();
                }else{
                    ThemDiem(urlThem);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void ThemDiem(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(ThemDiemActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ThemDiemActivity.this, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemDiemActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ten", edtTen.getText().toString().trim());
                params.put("diem", edtDiem.getText().toString().trim());
                params.put("monhoc_id", txtIdMon.getText().toString().trim() );
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        txtIdMon    =(TextView)     findViewById(R.id.textIdMon);
        edtTen      = (EditText)    findViewById(R.id.editTextTen);
        edtDiem     = (EditText)    findViewById(R.id.editTextDiem);
        edtMonHoc   = (EditText)    findViewById(R.id.editTextMon);
        btnNhap     = (Button)      findViewById(R.id.buttonThem);
        btnHuy      = (Button)      findViewById(R.id.buttonHuy);
    }
}