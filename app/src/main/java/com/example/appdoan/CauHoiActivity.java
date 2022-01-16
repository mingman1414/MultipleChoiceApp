package com.example.appdoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class CauHoiActivity extends AppCompatActivity {

    private TextView txtCauHoi;
    private TextView txtDiem;
    private TextView txtThuTuCauHoi;
    private TextView txtMonHoc;
    private TextView txtDemNguoc;

    private RadioGroup radgrpCauTraLoi;
    private RadioButton radA;
    private RadioButton radB;
    private RadioButton radC;
    private RadioButton radD;

    private Button btnTraLoi;

    private CountDownTimer countDownTimer;
    private ArrayList<CauHoi> arrayCauHoi;

    private long thoiGianConLai;
    private int cauhoiCounter;

    private int tongsoCauHoi = 10;
    private int diem = 0;

    private boolean traloi;
    private CauHoi cauHoiHienTai;
    private int count  = 0;


    String urlGetData = "http://192.168.1.14:8080/DoAnAndroid/getcauhoi.php?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
        AnhXa();

        //nhận tên môn học từ main
        Intent intent = getIntent();
        int monhocID = intent.getIntExtra("id", 0);
        String tenMonHoc = intent.getStringExtra("tenmonhoc");

        txtMonHoc.setText("Môn học: " + tenMonHoc);
        //list câu hỏi theo id
        GetData(urlGetData + monhocID);
        btnTraLoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!traloi){
                    if(radA.isChecked() || radB.isChecked() || radC.isChecked() || radD.isChecked()){
                        kiemtraDapAn();
                    }
                    else{
                        Toast.makeText(CauHoiActivity.this, "Hãy chọn đáp án!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    cauHoiTiepTheo();
                }
            }
        });
    }

    private void cauHoiTiepTheo() {
        radA.setTextColor(Color.BLACK);
        radB.setTextColor(Color.BLACK);
        radC.setTextColor(Color.BLACK);
        radD.setTextColor(Color.BLACK);

        radgrpCauTraLoi.clearCheck();

        if(cauhoiCounter < tongsoCauHoi){
            //lấy dữ liệu ở vị trí counter
            cauHoiHienTai = arrayCauHoi.get(cauhoiCounter);

            txtCauHoi.setText(cauHoiHienTai.getCauhoi());
            radA.setText("A. " + cauHoiHienTai.getDapana());
            radA.setBackgroundResource(R.drawable.round_back_white_stroke);
            radB.setText("B. " + cauHoiHienTai.getDapanb());
            radB.setBackgroundResource(R.drawable.round_back_white_stroke);
            radC.setText("C. " + cauHoiHienTai.getDapanc());
            radC.setBackgroundResource(R.drawable.round_back_white_stroke);
            radD.setText("D. " + cauHoiHienTai.getDapand());
            radD.setBackgroundResource(R.drawable.round_back_white_stroke);

            cauhoiCounter++;

            txtThuTuCauHoi.setText("Câu hỏi: " + cauhoiCounter + "/" + tongsoCauHoi);
            //false->chưa trả lời, đang show
            traloi = false;

            btnTraLoi.setText("Trả lời");

            //30 giây
            thoiGianConLai = 30000;

            startCountDown();
        }
        else
        {
//            ketThucCauHoi();
            startKetQua();
        }
    }

    private void ketThucCauHoi() {
        //trả về main
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("diem", diem);
//        setResult(RESULT_OK, resultIntent);
//        finish();

    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(thoiGianConLai, 1000) {
            @Override
            public void onTick(long l) {
                thoiGianConLai = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                thoiGianConLai = 0;
                updateCountDownText();
                kiemtraDapAn();
            }
        }.start();
    }

    private void kiemtraDapAn() {
        //đã trả lời
        traloi = true;
        //trả về radio button trong group được chọn
        RadioButton rbSelected = findViewById(radgrpCauTraLoi.getCheckedRadioButtonId());

        int dapan = radgrpCauTraLoi.indexOfChild(rbSelected) + 1;

        if(dapan == cauHoiHienTai.getDapandung()){
            diem = diem + 10;
            txtDiem.setText("Điểm: " + diem);
        }
        showDapAn();
    }

    private void showDapAn() {
        radA.setTextColor(Color.WHITE);
        radA.setBackgroundResource(R.drawable.round_back_red);
        radB.setTextColor(Color.WHITE);
        radB.setBackgroundResource(R.drawable.round_back_red);
        radC.setTextColor(Color.WHITE);
        radC.setBackgroundResource(R.drawable.round_back_red);
        radD.setTextColor(Color.WHITE);
        radD.setBackgroundResource(R.drawable.round_back_red);

        switch(cauHoiHienTai.getDapandung()){
            case 1:
                radA.setTextColor(Color.WHITE);
                radA.setBackgroundResource(R.drawable.round_back_green);
                txtCauHoi.setText("Đáp án là A");
                break;
            case 2:
                radB.setTextColor(Color.WHITE);
                radB.setBackgroundResource(R.drawable.round_back_green);
                txtCauHoi.setText("Đáp án là B");
                break;
            case 3:
                radC.setTextColor(Color.WHITE);
                radC.setBackgroundResource(R.drawable.round_back_green);
                txtCauHoi.setText("Đáp án là C");
                break;
            case 4:
                radD.setTextColor(Color.WHITE);
                radD.setBackgroundResource(R.drawable.round_back_green);
                txtCauHoi.setText("Đáp án là D");
                break;
        }

        if(cauhoiCounter < tongsoCauHoi){
            btnTraLoi.setText("Câu tiếp theo");
        }
        else{
            btnTraLoi.setText("Kết thúc");
        }

        //dừng đồng hồ
        countDownTimer.cancel();
    }

    private void updateCountDownText() {
        int phut = (int) ((thoiGianConLai / 10000) / 60);
        int giay = (int) ((thoiGianConLai / 1000) % 60);
        String thoigian = String.format(Locale.getDefault(), "%02d:%02d", phut, giay);
        txtDemNguoc.setText(thoigian);

        if(thoiGianConLai < 10000){
            txtDemNguoc.setTextColor(Color.RED);
        }
        else{
            txtDemNguoc.setTextColor(Color.BLACK);
        }
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(CauHoiActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                arrayCauHoi = new ArrayList<>();
                for(int i=0; i<response.length(); i++){
                    try{
                        JSONObject object = response.getJSONObject(i);
                        arrayCauHoi.add(new CauHoi(
                                object.getInt("id"),
                                object.getString("cauhoi"),
                                object.getString("dapana"),
                                object.getString("dapanb"),
                                object.getString("dapanc"),
                                object.getString("dapand"),
                                object.getInt("dapandung"),
                                object.getInt("monhoc_id")
                                ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                cauHoiTiepTheo();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CauHoiActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    //nút back


    @Override
    public void onBackPressed() {
        count++;
        if(count >= 1){
            ketThucCauHoi();
        }
        count = 0;
    }

    private void startKetQua(){
        //chuyển đến activity ket qua
        Intent intent = new Intent(CauHoiActivity.this, KetQuaActivity.class);
        intent.putExtra("diem", diem);

        //start for result để có thể nhận lại kết quả trả về
//        startActivityForResult(intent,REQUEST_CODE_QUESTION);
        startActivity(intent);
    }

    private void AnhXa() {
        txtCauHoi       = (TextView)    findViewById(R.id.textViewCauHoi);
        txtDiem         = (TextView)    findViewById(R.id.textViewDiem);
        txtThuTuCauHoi  = (TextView)    findViewById(R.id.textViewThuTuCauHoi);
        txtMonHoc       = (TextView)    findViewById(R.id.textViewMonHoc);
        txtDemNguoc     = (TextView)    findViewById(R.id.textViewDongHo);
        radgrpCauTraLoi = (RadioGroup)  findViewById(R.id.radioGroupDapAn);
        radA            = (RadioButton) findViewById(R.id.radioButton1);
        radB            = (RadioButton) findViewById(R.id.radioButton2);
        radC            = (RadioButton) findViewById(R.id.radioButton3);
        radD            = (RadioButton) findViewById(R.id.radioButton4);
        btnTraLoi           = (Button)      findViewById(R.id.buttonTraLoi);
    }
}