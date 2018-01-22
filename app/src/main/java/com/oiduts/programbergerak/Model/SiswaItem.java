package com.oiduts.programbergerak.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SiswaItem implements Parcelable {

	@SerializedName("telp")
	private String telp;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nis")
	private int nis;

	@SerializedName("id")
	private int id;

	@SerializedName("alamat")
	private String alamat;

	public void setTelp(String telp){
		this.telp = telp;
	}

	public String getTelp(){
		return telp;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNis(int nis){
		this.nis = nis;
	}

	public int getNis(){
		return nis;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"SiswaItem{" + 
			"telp = '" + telp + '\'' + 
			",nama = '" + nama + '\'' + 
			",nis = '" + nis + '\'' + 
			",id = '" + id + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.telp);
		dest.writeString(this.nama);
		dest.writeInt(this.nis);
		dest.writeInt(this.id);
		dest.writeString(this.alamat);
	}

	public SiswaItem() {
	}

	protected SiswaItem(Parcel in) {
		this.telp = in.readString();
		this.nama = in.readString();
		this.nis = in.readInt();
		this.id = in.readInt();
		this.alamat = in.readString();
	}

	public static final Parcelable.Creator<SiswaItem> CREATOR = new Parcelable.Creator<SiswaItem>() {
		@Override
		public SiswaItem createFromParcel(Parcel source) {
			return new SiswaItem(source);
		}

		@Override
		public SiswaItem[] newArray(int size) {
			return new SiswaItem[size];
		}
	};
}