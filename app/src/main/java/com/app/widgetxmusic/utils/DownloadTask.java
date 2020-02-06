package com.app.widgetxmusic.utils;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class DownloadTask extends AsyncTask<String, Integer, Void> {

    @Override
    protected Void doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            Log.i("progress", urls[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            String[] path = url.getPath().split("/");
            Log.i("progress", Arrays.toString(path));
            String mp3 = path[path.length-1];
            int lengthOfFile = c.getContentLength();

            String PATH = Environment.getExternalStorageDirectory() + "/Download/";
            Log.i("", "PATH: " + PATH);
            File file = new File(PATH);
            file.mkdirs();

            String fileName = mp3;

            File outputFile = new File(file, fileName);
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();
            Log.i("progress", PATH + fileName);
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {

                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();

            Log.i("progress", "Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}