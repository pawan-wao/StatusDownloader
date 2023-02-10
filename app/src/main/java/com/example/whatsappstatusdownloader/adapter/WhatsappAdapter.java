package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.Util;
import com.example.whatsappstatusdownloader.databinding.WhatsappItemLayoutBinding;
import com.example.whatsappstatusdownloader.model.WhatsappStatusModel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WhatsappAdapter extends RecyclerView.Adapter<WhatsappAdapter.ViewHolder> {

    private ArrayList<WhatsappStatusModel> list;
    private Context context;
    private LayoutInflater inflater;
    private String saveFilePath;
    private String getSaveFilePath = Util.RootDirectoryWhatsapp+"/";

    public WhatsappAdapter(ArrayList<WhatsappStatusModel> list, Context context){
        this.list = list;
        this.context= context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (inflater== null){
           inflater = LayoutInflater.from(parent.getContext());
       }
        return new ViewHolder (
            DataBindingUtil.inflate(inflater,
                    R.layout.whatsapp_item_layout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WhatsappStatusModel item = list.get(position);
        if(item.getUri().toString().endsWith(".mp4"))
            holder.binding.playButton.setVisibility(View.VISIBLE);
        else holder.binding.playButton.setVisibility(View.GONE);

        Glide.with(context).load(item.getPath()).into(holder.binding.statusimage);

        holder.binding.download.setOnClickListener(v -> {
            Util.createFileFolder();
            final String path = item.getPath();
            final File file = new File(path);
            File destFile = new File(saveFilePath);

            try{
                FileUtils.copyFileToDirectory(file, destFile);
            } catch (IOException e ){
                e.printStackTrace();
            }
            Toast.makeText(context, "saved to : "+saveFilePath, Toast.LENGTH_SHORT).show();



        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

    WhatsappItemLayoutBinding binding;

        public ViewHolder(WhatsappItemLayoutBinding binding){
            super(binding.getRoot());
            this.binding=binding;


        }
    }
}
