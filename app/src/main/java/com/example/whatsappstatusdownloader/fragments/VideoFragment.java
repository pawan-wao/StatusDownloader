package com.example.whatsappstatusdownloader.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.adapter.WhatsappAdapter;
import com.example.whatsappstatusdownloader.databinding.FragmentImageBinding;
import com.example.whatsappstatusdownloader.model.WhatsappStatusModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class VideoFragment extends Fragment {



    private FragmentImageBinding binding;
    private ArrayList<WhatsappStatusModel> list;
    private WhatsappAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image,
                container,false);

        list = new ArrayList<>();
        getData();

        binding.refresh.setOnRefreshListener(() -> {
            list = new ArrayList<>();
            getData();
            binding.refresh.setRefreshing(false);
        });

        return binding.getRoot();


    }
    private void getData(){

        WhatsappStatusModel model;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath()+
                "/Whatsapp/Media/.statuses";
        File targetDirector = new File(targetPath);
        File[] allFiles = targetDirector.listFiles();

        String targetPathBusiness = Environment.getExternalStorageDirectory().getAbsolutePath()+
                "/Whatsapp/Media/.statuses";
        File targetDirectorBusiness = new File(targetPathBusiness);
        File[] allFilesBusiness = targetDirectorBusiness.listFiles();


        //for whatsapp
        Arrays.sort(allFiles, (((o1, o2) ->{
            if (o1.lastModified() > o2.lastModified()) return -1;
            else if (o1.lastModified() < o2.lastModified()) return +1;
            else return 0;
        })));

        for (int i = 0; i<allFiles.length; i++){
            File file = allFiles[i];
            if (Uri.fromFile(file).toString().endsWith(".mp4") ){
                model= new WhatsappStatusModel("whats"+i,
                        Uri.fromFile(file),
                        allFiles[i].getAbsolutePath(),
                        file.getName());
                list.add(model);
            }

        }

        //for whatsapp business
        Arrays.sort(allFilesBusiness, (((o1, o2) ->{
            if (o1.lastModified() > o2.lastModified()) return -1;
            else if (o1.lastModified() < o2.lastModified()) return +1;
            else return 0;
        })));

        for (int i = 0; i<allFilesBusiness.length; i++){
            File file = allFilesBusiness[i];
            if (Uri.fromFile(file).toString().endsWith(".mp4") ){
                model= new WhatsappStatusModel("whatsBusiness"+i,
                        Uri.fromFile(file),
                        allFilesBusiness[i].getAbsolutePath(),
                        file.getName());
                list.add(model);
            }

        }

    }
}