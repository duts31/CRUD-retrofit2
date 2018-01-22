package com.oiduts.programbergerak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oiDutS on 20/01/2018.
 */

public class SiswaResponse {

    @SerializedName("siswa")
    private List<SiswaItem> siswa;

    @SerializedName("error_status")
    private boolean errorStatus;

    @SerializedName("data_count")
    private int dataCount;

    public void setSiswa(List<SiswaItem> siswa){
        this.siswa = siswa;
    }

    public List<SiswaItem> getSiswa(){
        return siswa;
    }

    public void setErrorStatus(boolean errorStatus){
        this.errorStatus = errorStatus;
    }

    public boolean isErrorStatus(){
        return errorStatus;
    }

    public void setDataCount(int dataCount){
        this.dataCount = dataCount;
    }

    public int getDataCount(){
        return dataCount;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "siswa = '" + siswa + '\'' +
                        ",error_status = '" + errorStatus + '\'' +
                        ",data_count = '" + dataCount + '\'' +
                        "}";
    }

}
