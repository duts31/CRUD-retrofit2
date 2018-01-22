package com.oiduts.programbergerak.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oiduts.programbergerak.Activity.AddActivity;
import com.oiduts.programbergerak.Model.SiswaItem;
import com.oiduts.programbergerak.Model.SiswaResponse;
import com.oiduts.programbergerak.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oiDutS on 20/01/2018.
 */

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>{

    Context context;
    List<SiswaItem> list;

    public SiswaAdapter(Context context, List<SiswaItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_siswa,parent,false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SiswaViewHolder holder, int position) {
        String nis =  String.valueOf(list.get(position).getNis());
        String nama = list.get(position).getNama();

        holder.tv_nis.setText(nis);
        holder.tv_nama.setText(nama);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SiswaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_listItem)CardView cv_item;
        @BindView(R.id.tv_nis)TextView tv_nis;
        @BindView(R.id.tv_nama)TextView tv_nama;

        public SiswaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            cv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    final SiswaItem siswaItem = list.get(pos);
                    String name = siswaItem.getNama();

                    final CharSequence[] dialogitem = {"Edit Data", "Hapus Data"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(name);
                    builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    Intent intent = new Intent(context, AddActivity.class);
                                    intent.putExtra(AddActivity.ACTION,"UPDATE");
                                    intent.putExtra(AddActivity.SISWA_DATA, siswaItem);
                                    context.startActivity(intent);
                                    break;
                                case 1:
                                    Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show();
                                    //notifyDataSetChanged();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
            });
        }
    }
}
