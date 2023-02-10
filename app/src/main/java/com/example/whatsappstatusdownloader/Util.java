package com.example.whatsappstatusdownloader;

import android.os.Environment;

import java.io.File;

public class Util {

    public static File RootDirectoryWhatsapp =
            new File(Environment.getExternalStorageDirectory() +
                    "/Download/WhatsappStatusDownloader/Whatsapp");

    public static void createFileFolder(){
        if (!RootDirectoryWhatsapp.exists())
            RootDirectoryWhatsapp.mkdir();
    }
}
