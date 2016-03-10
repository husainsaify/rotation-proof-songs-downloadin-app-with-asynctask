package com.hackerkernel.downloadappasynctask;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//Async task to download song
class MyTask extends AsyncTask<String, Integer, Boolean> {
    private int contentlength = -1;
    private int counter = 0;
    private int current = 0;

    private Activity activity;

    public MyTask(Activity activity){
        this.activity = activity;
    }

    public void onAttach(Activity activity){
        this.activity = activity;
    }

    public void onDetach(){
        activity = null;
    }

    @Override
    protected void onPreExecute() {
        if (activity != null){
            ((MainActivity)activity).showProgressbar();
        }
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean success = false;
        URL downloadUrl = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;

        try {
            downloadUrl = new URL(params[0]);
            connection = (HttpURLConnection) downloadUrl.openConnection();
            contentlength = connection.getContentLength();
            inputStream = connection.getInputStream();
            file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + "/" + Uri.parse(params[0]).getLastPathSegment());
            Log.d("", "HUS: " + file.getAbsolutePath());
            fileOutputStream = new FileOutputStream(file);
            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);
                //Log.d("HUS","HUS: "+read);
                //update counter
                counter += read;

                publishProgress(counter);
            }
            success = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (activity != null){
            current = (int) (((double) values[0] / contentlength) * 100);
            ((MainActivity)activity).updateProgressbar(current);
        }


    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (activity != null){
            ((MainActivity)activity).hideProgressbar();
        }
    }
}
