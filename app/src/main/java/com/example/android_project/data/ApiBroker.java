package com.example.android_project.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiBroker {
    public interface ApiResponseListener {
        void onApiResponse(String response) throws JSONException;
    }
    public static void fetchData(String apiUrl, ApiResponseListener listener) {
        new FetchDataTask(apiUrl, listener).execute();
    }
    private static class FetchDataTask extends AsyncTask<Void, Void, String> {
        private final String apiUrl;
        private final ApiResponseListener listener;

        FetchDataTask(String apiUrl, ApiResponseListener listener) {
            this.apiUrl = apiUrl;
            this.listener = listener;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set up the connection
                connection.setRequestMethod("GET");
                connection.connect();

                // Get the response
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }

                // Close the streams and disconnect the connection
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String response)  {
            if (response != null) {
                try {
                    listener.onApiResponse(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Handle error
                try {
                    listener.onApiResponse("Error fetching data from API");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public interface ImageResponseListener {
        void onImageResponse(Bitmap image);
    }

    public static void fetchImage(String imageUrl, ImageResponseListener listener) {
        new DownloadImageFromInternet(imageUrl, listener).execute();
    }

    private static class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        private final String imageUrl;
        private final ImageResponseListener listener;

        public DownloadImageFromInternet(String imageUrl, ImageResponseListener listener) {
            this.imageUrl = imageUrl;
            this.listener = listener;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(this.imageUrl).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            listener.onImageResponse(result);
        }

    }


}
