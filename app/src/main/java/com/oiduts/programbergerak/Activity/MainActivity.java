package com.oiduts.programbergerak.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.oiduts.programbergerak.Adapter.SiswaAdapter;
import com.oiduts.programbergerak.Api.ApiClient;
import com.oiduts.programbergerak.Api.ApiInterface;
import com.oiduts.programbergerak.Model.SiswaItem;
import com.oiduts.programbergerak.Model.SiswaResponse;
import com.oiduts.programbergerak.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SiswaAdapter adapter;
    List<SiswaItem> siswaResponseList = new ArrayList<>();
    Call<SiswaResponse> call;

    @BindView(R.id.rv_siswa)RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra(AddActivity.ACTION,"POST");
                startActivity(intent);
            }
        });

        Log.e("VERSION","1.0.9");

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        adapter = new SiswaAdapter(this, siswaResponseList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataSiswa();
        Toast.makeText(MainActivity.this, "On Resume",Toast.LENGTH_SHORT).show();
    }

    public void loadDataSiswa() {
        siswaResponseList.clear();
        final ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(
                MainActivity.this,
                "Memuat data",
                "Mohon bersabar ini ujian..",
                false,false
        );

        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        call = apiService.getDataSiswa();
        call.enqueue(new Callback<SiswaResponse>() {
            @Override
            public void onResponse(Call<SiswaResponse> call, Response<SiswaResponse> response) {
                if (!response.isSuccessful()) {
                    call = call.clone();
                    call.enqueue(this);
                    return;
                }

                if (response.body() == null)return;

                for (SiswaItem siswaItem : response.body().getSiswa()) {
                    if (siswaItem.getNama() != null) {
                        siswaResponseList.add(siswaItem);
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SiswaResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "ERROR FETCHING DATA", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
