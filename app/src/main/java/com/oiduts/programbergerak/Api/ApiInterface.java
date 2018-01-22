package com.oiduts.programbergerak.Api;

import com.oiduts.programbergerak.Model.QueryResponse;
import com.oiduts.programbergerak.Model.SiswaResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by oiDutS on 20/01/2018.
 */

public interface ApiInterface {

    @GET("read_siswa.php")
    Call<SiswaResponse> getDataSiswa();

    @FormUrlEncoded
    @POST("add_siswa.php")
    Call<QueryResponse> addSiswa(
            @Field("nis") int nis,
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("update_siswa.php")
    Call<QueryResponse> updateSiswa(
            @Field("id") int id,
            @Field("nis") int nis,
            @Field("nama") String nama,
            @Field("telp") String telp,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("delete_siswa.php")
    Call<QueryResponse> deleteSiswa(
            @Field("id") int id
    );
}
