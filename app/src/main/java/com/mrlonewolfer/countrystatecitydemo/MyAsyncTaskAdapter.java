package com.mrlonewolfer.countrystatecitydemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.AdapterView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class MyAsyncTaskAdapter extends AsyncTask<String,Void,String> {

    public Context context;
    ProgressDialog  progressDialog;
    String spinnerType;

    public MyAsyncTaskAdapter(Context context, OnResponseListner listner, String spinnerType) {
        this.context = context;
        this.listner = listner;
        this.spinnerType=spinnerType;
    }


    public interface  OnResponseListner{
        void Response(String data,String spinnerType);
    }

    OnResponseListner listner;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(context,"Wait","Fetching Data");
    }

    @Override
    protected String doInBackground(String... strings) {

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(strings[0]).build();


        try {
            Response response=client.newCall(request).execute();
            String data=response.body().string();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        listner.Response(s,spinnerType);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
