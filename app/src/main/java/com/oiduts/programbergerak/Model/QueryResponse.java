package com.oiduts.programbergerak.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oiDutS on 20/01/2018.
 */

public class QueryResponse {

    @SerializedName("error_status")
    private boolean errorStatus;

    @SerializedName("message")
    private String message;

    public void setErrorStatus(boolean errorStatus){
        this.errorStatus = errorStatus;
    }

    public boolean isErrorStatus(){
        return errorStatus;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "error_status = '" + errorStatus + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
