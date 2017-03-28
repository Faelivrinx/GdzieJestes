package com.gdziejestes.async;

import android.os.AsyncTask;
import android.util.Log;

import com.gdziejestes.interfaces.IAsyncLogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Damrod on 28.03.2017.
 */

public class AsyncLogin extends AsyncTask <String, Void, String> {
    String jsonUrl = "http://damrod.16mb.com/android/gdzie-jestes/database-login.php";
    String jsonString;

    public IAsyncLogin delegate;

    @Override
    protected String doInBackground(String... params) {

        String username = params[0];
        String password = params[1];
        Log.w("AsyncLogin", "u: "+username+"\np: "+password );
        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

            bufferedWriter.write(data);
            Log.w("AsyncLogin", data );
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            Log.w("AsyncLogin",stringBuilder.toString().trim() );
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public AsyncLogin() {
        super();
    }

    public AsyncLogin(IAsyncLogin delegate){
        this.delegate = delegate;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
