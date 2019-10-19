package com.oiduts.programbergerak.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oiduts.programbergerak.Api.ApiClient;
import com.oiduts.programbergerak.Api.ApiInterface;
import com.oiduts.programbergerak.Model.QueryResponse;
import com.oiduts.programbergerak.Model.SiswaItem;
import com.oiduts.programbergerak.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String ACTION = "action";
    public static final String SISWA_DATA = "siswa";

    @BindView(R.id.btn_submit)Button btn_submit;

    @BindView(R.id.et_nis)EditText et_nis;
    @BindView(R.id.et_nama)EditText et_nama;
    @BindView(R.id.et_telp)EditText et_telp;
    @BindView(R.id.et_alamat)EditText et_alamat;

    Intent intent;
    String submitAction;
    private SiswaItem siswaItem;
    String id_siswa;

    Call<QueryResponse> queryCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        submitAction = intent.getStringExtra(ACTION);

        if (submitAction.equals("UPDATE")) {
            setContent();
        }

        btn_submit.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_submit) {
            if (submitAction.equals("POST")) {
                addSiswa();
            } else {
                updateSiswa();
            }
        }
    }

    public void setContent() {
        siswaItem = intent.getExtras().getParcelable(SISWA_DATA);
        String nis = String.valueOf(siswaItem.getNis());
        String nama = siswaItem.getNama();
        String telp = siswaItem.getTelp();
        String alamat = siswaItem.getAlamat();
        id_siswa = String.valueOf(siswaItem.getId());

        et_nis.setText(nis);
        et_nama.setText(nama);
        et_telp.setText(telp);
        et_alamat.setText(alamat);
    }

    public void addSiswa() {
        final String nis = et_nis.getText().toString().trim();
        final String nama = et_nama.getText().toString().trim();
        final String telp = et_telp.getText().toString().trim();
        final String alamat = et_alamat.getText().toString().trim();

        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        queryCall = apiService.addSiswa(Integer.parseInt(nis), nama, telp, alamat);
        queryCall.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                if (!response.isSuccessful()) {
                    queryCall = call.clone();
                    queryCall.enqueue(this);
                    return;
                }

                if (response.body() == null) return;

                if (response.body().isErrorStatus()) {
                    Toast.makeText(AddActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                Toast.makeText(AddActivity.this, "ERROR FETCHING DATA", Toast.LENGTH_SHORT).show();
            }
        });

//        ProgressDialog progressDialog;
//        progressDialog = ProgressDialog.show(
//                AddActivity.this,
//                "Menambahkan..",
//                "Mohon bersabar ini ujian..",
//                false,false
//        );
    }

    public void updateSiswa() {
        final String nis = et_nis.getText().toString().trim();
        final String nama = et_nama.getText().toString().trim();
        final String telp = et_telp.getText().toString().trim();
        final String alamat = et_alamat.getText().toString().trim();

        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        queryCall = apiService.updateSiswa(Integer.parseInt(id_siswa), Integer.parseInt(nis), nama, telp, alamat);
        queryCall.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                if (!response.isSuccessful()) {
                    queryCall = call.clone();
                    queryCall.enqueue(this);
                    return;
                }

                if (response.body() == null) return;

                if (response.body().isErrorStatus()) {
                    Toast.makeText(AddActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                Toast.makeText(AddActivity.this, "ERROR FETCHING DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
