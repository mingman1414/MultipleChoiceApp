package com.example.appdoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtDiemCao;
    Spinner spinnerMonHoc;
    private Button btnBatDau;
    ArrayList<MonHoc> arrayMonHoc;
    ArrayList<String> tenmonhoc = new ArrayList<String>();
    int vitrimon = 1;

    private static final int REQUEST_CODE_QUESTION = 1;

    String urlGetData = "http://192.168.1.13:8080/DoAnAndroid/getmonhoc.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetData(urlGetData);

        //chọn môn
        spinnerMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vitrimon = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCauHoi();
            }
        });
    }

    private void AnhXa() {
        txtDiemCao      = (TextView)    findViewById(R.id.textviewDiemCao);
        btnBatDau       = (Button)      findViewById(R.id.buttonBatDau);
        spinnerMonHoc   = (Spinner)     findViewById(R.id.spinnerMonHoc);
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                /*Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();*/
                arrayMonHoc = new ArrayList<>();
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayMonHoc.add(new MonHoc(
                                object.getInt("id"),
                                object.getString("tenmonhoc")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // đổ dữ liệu spinner
                for(int i=0; i<arrayMonHoc.size(); i++){
                    tenmonhoc.add(arrayMonHoc.get(i).getTenmonhoc().toString());
                }
                ArrayAdapter<String> tenmonhocAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, tenmonhoc);
                tenmonhocAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMonHoc.setAdapter(tenmonhocAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void startCauHoi(){
        //lấy id, tên môn học
        String tenMonHoc = spinnerMonHoc.getSelectedItem().toString();
//        Toast.makeText(MainActivity.this, tenMonHoc, Toast.LENGTH_SHORT).show();
        //chuyển đến activity câu hỏi
        Intent intent = new Intent(MainActivity.this, CauHoiActivity.class);
        intent.putExtra("id", vitrimon);
        intent.putExtra("tenmonhoc", tenMonHoc);

        //start for result để có thể nhận lại kết quả trả về
        startActivityForResult(intent,REQUEST_CODE_QUESTION);
    }
}